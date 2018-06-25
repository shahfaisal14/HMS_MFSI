import { Injectable } from '@angular/core';
import { ConnectionBackend, RequestOptions, Request, RequestOptionsArgs, Response, Http, Headers} from "@angular/http"
import { Observable } from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/finally';

import { environment } from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService extends Http {

  constructor(backend: ConnectionBackend, defaultOptions: RequestOptions) { 
    super(backend, defaultOptions);
  }

  request(url: string | Request, options?: RequestOptionsArgs) : Observable<Response>{
    return super.request(url, options);
  }

  get(url: string, options?: RequestOptionsArgs) : Observable<Response>{
    url = this.updateUrl(url);
    return super.get(url, this.getRequestOptionArgs(options));
  }

  post(url: string, body: string, options?: RequestOptionsArgs): Observable<any> {
    url = this.updateUrl(url);
    return super.post(url, body, this.getRequestOptionArgs(options))
      .catch(this.onCatch)
      .do((res: Response) => {
          this.onSubscribeSuccess(res);
      }, (error: any) => {
          this.onSubscribeError(error);
      })
      .finally(() => {
          this.onFinally();
      });
  }

  put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
      url = this.updateUrl(url);
      return super.put(url, body, this.getRequestOptionArgs(options));
  }

  delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
      url = this.updateUrl(url);
      return super.delete(url, this.getRequestOptionArgs(options));
  }

  private updateUrl(req: string) {
    return  environment.origin + req;
  }

  private getRequestOptionArgs(options?: RequestOptionsArgs) : RequestOptionsArgs {
    if (options == null) {
        options = new RequestOptions();
    }
    if (options.headers == null) {
        options.headers = new Headers();
    }
    options.headers.append('Content-Type', 'application/json');

    return options;
  }

  private requestInterceptor(): void {
    // this.loaderService.showPreloader();
  }

  private responseInterceptor(): void {
    // this.loaderService.hidePreloader();
  }

  private onCatch(error: any, caught: Observable<any>) : Observable<any>{
    return Observable.throw(error);
  }

  private onSubscribeSuccess(res: Response): void {
    let responseFromServer = JSON.parse(res._body);
    if(responseFromServer.responseType == 'ERROR'){
      alert(responseFromServer.errorDescription);
    }
  }

  private onSubscribeError(error: any): void {

  }

  private onFinally(): void {
    this.responseInterceptor();
  }
}

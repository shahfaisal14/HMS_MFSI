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
    let opt = this.getRequestOptionArgs(options);
    return super.get(url, opt)
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

  post(url: string, body: any, options?: RequestOptionsArgs): Observable<any> {
    url = this.updateUrl(url);
    let opt = this.getRequestOptionArgs(options);
    return super.post(url, body, opt)
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
    let opt = this.getRequestOptionArgs(options);
      return super.delete(url, opt)
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

  private updateUrl(req: string) {
    return  environment.origin + req;
  }

  private getRequestOptionArgs(options?: RequestOptionsArgs) : RequestOptionsArgs {
    let authToken = localStorage.getItem('token');
    
    options = new RequestOptions({
      headers:new Headers({ 
        'Content-Type': 'application/json',
        'X-Auth-Token': authToken
      })
    });

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

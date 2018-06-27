import { XHRBackend, Http, RequestOptions } from "@angular/http";
import { HttpInterceptorService } from "./http.interceptor";

export function httpFactory(xhrBackend: XHRBackend, requestOptions: RequestOptions){
    return new HttpInterceptorService(xhrBackend, requestOptions);
}
(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");



var routes = [
    { path: '', redirectTo: '/welcome', pathMatch: 'full' }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* Button used to open the contact form - fixed at the bottom of the page */\n.open-button {\n    background-color: #555;\n    color: white;\n    padding: 16px 20px;\n    border: none;\n    cursor: pointer;\n    opacity: 0.8;\n    position: fixed;\n    top: 50%;\n    left: 50%;\n    margin-top: -50px;\n    margin-left: -100px;\n}\n/* The popup form - hidden by default */\n.form-popup {\n    display: none;\n    position: fixed;\n    top: 10%;\n    left: 50%;\n    margin-top: -50px;\n    margin-left: -100px;\n}\n/* Add styles to the form container */\n.form-container {\n    max-width: 300px;\n    padding: 10px;\n    background-color: #d5d5d5;\n}\n/* Full-width input fields */\n.form-container input[type=text], .form-container input[type=password] {\n    width: 100%;\n    padding: 15px;\n    margin: 5px 0 22px 0;\n    border: none;\n    background: #ffffff;\n}\n/* When the inputs get focus, do something */\n.form-container input[type=text]:focus, .form-container input[type=password]:focus {\n    background-color: #ffffff;\n    outline: none;\n}\n/* Set a style for the submit/login button */\n.form-container .btn {\n    background-color: #4CAF50;\n    color: white;\n    padding: 16px 20px;\n    border: none;\n    cursor: pointer;\n    width: 100%;\n    margin-bottom:10px;\n    opacity: 0.8;\n}\n/* Add a red background color to the cancel button */\n.form-container .cancel {\n    background-color: red;\n}\n/* Add some hover effects to buttons */\n.form-container .btn:hover, .open-button:hover {\n    opacity: 1;\n}\n.hiddenCancelButton {\n    display: none;\n    background-color: red;\n    color: white;\n}\n.cardImgContainer {\n    position: relative;\n    text-align: center;\n}\n.bottom-left {\n    text-shadow:\n            -1px -1px 0 #000,\n            1px -1px 0 #000,\n            -1px 1px 0 #000,\n            1px 1px 0 #000;\n    position: absolute;\n    bottom: 8px;\n    left: 16px;\n    color: red;\n}\n.bottom-right {\n    text-shadow:\n            -1px -1px 0 #000,\n            1px -1px 0 #000,\n            -1px 1px 0 #000,\n            1px 1px 0 #000;\n    position: absolute;\n    bottom: 8px;\n    right: 16px;\n    color: green;\n}\n.top-right {\n    text-shadow:\n            -1px -1px 0 #000,\n            1px -1px 0 #000,\n            -1px 1px 0 #000,\n            1px 1px 0 #000;\n    position: absolute;\n    top: 8px;\n    right: 16px;\n    color: dodgerblue;\n}\n.card {\n     margin-left: 1vw;\n     display:inline-block;\n     height: 12vh;\n     vertical-align: top;\n}\n.targetDetails {\n    margin-left: 1vw;\n    display:inline-block;\n    height: 12vh;\n    vertical-align: top;\n    position: relative;\n}\n.card:hover {\n    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);\n}\n.contCard {\n    word-break: break-all;\n    font-size: 1vw;\n    padding-right: 0;\n    padding-left: 0;\n    margin-right: auto;\n    margin-left: auto;\n}\n/* Clearfix (clear floats) */\n.row{\n    margin: 0 auto;\n    text-align: center;\n    height: 14vh;\n}\n#theBoard{\n    display: none;\n    height: 84vh;\n}\n.taunt {\n    margin-left: 1vw;\n    border-top: solid #00ff24;\n    height: 12vh;\n}\n.canAttack {\n    margin-left: 1vw;\n    border-right: solid #ff0000;\n    height: 12vh;\n}\n.lifeSteal {\n    margin-left: 1vw;\n    border-left: solid #ffef00;\n    height: 12vh;\n}\n.heropower {\n    height: 8vh;\n    vertical-align: middle;\n}\n\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvYXBwLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUEsNEVBQTRFO0FBQzVFO0lBQ0ksdUJBQXVCO0lBQ3ZCLGFBQWE7SUFDYixtQkFBbUI7SUFDbkIsYUFBYTtJQUNiLGdCQUFnQjtJQUNoQixhQUFhO0lBQ2IsZ0JBQWdCO0lBQ2hCLFNBQVM7SUFDVCxVQUFVO0lBQ1Ysa0JBQWtCO0lBQ2xCLG9CQUFvQjtDQUN2QjtBQUVELHdDQUF3QztBQUN4QztJQUNJLGNBQWM7SUFDZCxnQkFBZ0I7SUFDaEIsU0FBUztJQUNULFVBQVU7SUFDVixrQkFBa0I7SUFDbEIsb0JBQW9CO0NBQ3ZCO0FBRUQsc0NBQXNDO0FBQ3RDO0lBQ0ksaUJBQWlCO0lBQ2pCLGNBQWM7SUFDZCwwQkFBMEI7Q0FDN0I7QUFFRCw2QkFBNkI7QUFDN0I7SUFDSSxZQUFZO0lBQ1osY0FBYztJQUNkLHFCQUFxQjtJQUNyQixhQUFhO0lBQ2Isb0JBQW9CO0NBQ3ZCO0FBRUQsNkNBQTZDO0FBQzdDO0lBQ0ksMEJBQTBCO0lBQzFCLGNBQWM7Q0FDakI7QUFFRCw2Q0FBNkM7QUFDN0M7SUFDSSwwQkFBMEI7SUFDMUIsYUFBYTtJQUNiLG1CQUFtQjtJQUNuQixhQUFhO0lBQ2IsZ0JBQWdCO0lBQ2hCLFlBQVk7SUFDWixtQkFBbUI7SUFDbkIsYUFBYTtDQUNoQjtBQUVELHFEQUFxRDtBQUNyRDtJQUNJLHNCQUFzQjtDQUN6QjtBQUVELHVDQUF1QztBQUN2QztJQUNJLFdBQVc7Q0FDZDtBQUVEO0lBQ0ksY0FBYztJQUNkLHNCQUFzQjtJQUN0QixhQUFhO0NBQ2hCO0FBQ0Q7SUFDSSxtQkFBbUI7SUFDbkIsbUJBQW1CO0NBQ3RCO0FBQ0Q7SUFDSTs7OzsyQkFJdUI7SUFDdkIsbUJBQW1CO0lBQ25CLFlBQVk7SUFDWixXQUFXO0lBQ1gsV0FBVztDQUNkO0FBRUQ7SUFDSTs7OzsyQkFJdUI7SUFDdkIsbUJBQW1CO0lBQ25CLFlBQVk7SUFDWixZQUFZO0lBQ1osYUFBYTtDQUNoQjtBQUVEO0lBQ0k7Ozs7MkJBSXVCO0lBQ3ZCLG1CQUFtQjtJQUNuQixTQUFTO0lBQ1QsWUFBWTtJQUNaLGtCQUFrQjtDQUNyQjtBQUVEO0tBQ0ssaUJBQWlCO0tBQ2pCLHFCQUFxQjtLQUNyQixhQUFhO0tBQ2Isb0JBQW9CO0NBQ3hCO0FBRUQ7SUFDSSxpQkFBaUI7SUFDakIscUJBQXFCO0lBQ3JCLGFBQWE7SUFDYixvQkFBb0I7SUFDcEIsbUJBQW1CO0NBQ3RCO0FBRUQ7SUFDSSx5Q0FBeUM7Q0FDNUM7QUFFRDtJQUNJLHNCQUFzQjtJQUN0QixlQUFlO0lBQ2YsaUJBQWlCO0lBQ2pCLGdCQUFnQjtJQUNoQixtQkFBbUI7SUFDbkIsa0JBQWtCO0NBQ3JCO0FBRUQsNkJBQTZCO0FBQzdCO0lBQ0ksZUFBZTtJQUNmLG1CQUFtQjtJQUNuQixhQUFhO0NBQ2hCO0FBRUQ7SUFDSSxjQUFjO0lBQ2QsYUFBYTtDQUNoQjtBQUVEO0lBQ0ksaUJBQWlCO0lBQ2pCLDBCQUEwQjtJQUMxQixhQUFhO0NBQ2hCO0FBRUQ7SUFDSSxpQkFBaUI7SUFDakIsNEJBQTRCO0lBQzVCLGFBQWE7Q0FDaEI7QUFFRDtJQUNJLGlCQUFpQjtJQUNqQiwyQkFBMkI7SUFDM0IsYUFBYTtDQUNoQjtBQUVEO0lBQ0ksWUFBWTtJQUNaLHVCQUF1QjtDQUMxQiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLyogQnV0dG9uIHVzZWQgdG8gb3BlbiB0aGUgY29udGFjdCBmb3JtIC0gZml4ZWQgYXQgdGhlIGJvdHRvbSBvZiB0aGUgcGFnZSAqL1xuLm9wZW4tYnV0dG9uIHtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjNTU1O1xuICAgIGNvbG9yOiB3aGl0ZTtcbiAgICBwYWRkaW5nOiAxNnB4IDIwcHg7XG4gICAgYm9yZGVyOiBub25lO1xuICAgIGN1cnNvcjogcG9pbnRlcjtcbiAgICBvcGFjaXR5OiAwLjg7XG4gICAgcG9zaXRpb246IGZpeGVkO1xuICAgIHRvcDogNTAlO1xuICAgIGxlZnQ6IDUwJTtcbiAgICBtYXJnaW4tdG9wOiAtNTBweDtcbiAgICBtYXJnaW4tbGVmdDogLTEwMHB4O1xufVxuXG4vKiBUaGUgcG9wdXAgZm9ybSAtIGhpZGRlbiBieSBkZWZhdWx0ICovXG4uZm9ybS1wb3B1cCB7XG4gICAgZGlzcGxheTogbm9uZTtcbiAgICBwb3NpdGlvbjogZml4ZWQ7XG4gICAgdG9wOiAxMCU7XG4gICAgbGVmdDogNTAlO1xuICAgIG1hcmdpbi10b3A6IC01MHB4O1xuICAgIG1hcmdpbi1sZWZ0OiAtMTAwcHg7XG59XG5cbi8qIEFkZCBzdHlsZXMgdG8gdGhlIGZvcm0gY29udGFpbmVyICovXG4uZm9ybS1jb250YWluZXIge1xuICAgIG1heC13aWR0aDogMzAwcHg7XG4gICAgcGFkZGluZzogMTBweDtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZDVkNWQ1O1xufVxuXG4vKiBGdWxsLXdpZHRoIGlucHV0IGZpZWxkcyAqL1xuLmZvcm0tY29udGFpbmVyIGlucHV0W3R5cGU9dGV4dF0sIC5mb3JtLWNvbnRhaW5lciBpbnB1dFt0eXBlPXBhc3N3b3JkXSB7XG4gICAgd2lkdGg6IDEwMCU7XG4gICAgcGFkZGluZzogMTVweDtcbiAgICBtYXJnaW46IDVweCAwIDIycHggMDtcbiAgICBib3JkZXI6IG5vbmU7XG4gICAgYmFja2dyb3VuZDogI2ZmZmZmZjtcbn1cblxuLyogV2hlbiB0aGUgaW5wdXRzIGdldCBmb2N1cywgZG8gc29tZXRoaW5nICovXG4uZm9ybS1jb250YWluZXIgaW5wdXRbdHlwZT10ZXh0XTpmb2N1cywgLmZvcm0tY29udGFpbmVyIGlucHV0W3R5cGU9cGFzc3dvcmRdOmZvY3VzIHtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZmZmZmZmO1xuICAgIG91dGxpbmU6IG5vbmU7XG59XG5cbi8qIFNldCBhIHN0eWxlIGZvciB0aGUgc3VibWl0L2xvZ2luIGJ1dHRvbiAqL1xuLmZvcm0tY29udGFpbmVyIC5idG4ge1xuICAgIGJhY2tncm91bmQtY29sb3I6ICM0Q0FGNTA7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIHBhZGRpbmc6IDE2cHggMjBweDtcbiAgICBib3JkZXI6IG5vbmU7XG4gICAgY3Vyc29yOiBwb2ludGVyO1xuICAgIHdpZHRoOiAxMDAlO1xuICAgIG1hcmdpbi1ib3R0b206MTBweDtcbiAgICBvcGFjaXR5OiAwLjg7XG59XG5cbi8qIEFkZCBhIHJlZCBiYWNrZ3JvdW5kIGNvbG9yIHRvIHRoZSBjYW5jZWwgYnV0dG9uICovXG4uZm9ybS1jb250YWluZXIgLmNhbmNlbCB7XG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmVkO1xufVxuXG4vKiBBZGQgc29tZSBob3ZlciBlZmZlY3RzIHRvIGJ1dHRvbnMgKi9cbi5mb3JtLWNvbnRhaW5lciAuYnRuOmhvdmVyLCAub3Blbi1idXR0b246aG92ZXIge1xuICAgIG9wYWNpdHk6IDE7XG59XG5cbi5oaWRkZW5DYW5jZWxCdXR0b24ge1xuICAgIGRpc3BsYXk6IG5vbmU7XG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmVkO1xuICAgIGNvbG9yOiB3aGl0ZTtcbn1cbi5jYXJkSW1nQ29udGFpbmVyIHtcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLmJvdHRvbS1sZWZ0IHtcbiAgICB0ZXh0LXNoYWRvdzpcbiAgICAgICAgICAgIC0xcHggLTFweCAwICMwMDAsXG4gICAgICAgICAgICAxcHggLTFweCAwICMwMDAsXG4gICAgICAgICAgICAtMXB4IDFweCAwICMwMDAsXG4gICAgICAgICAgICAxcHggMXB4IDAgIzAwMDtcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XG4gICAgYm90dG9tOiA4cHg7XG4gICAgbGVmdDogMTZweDtcbiAgICBjb2xvcjogcmVkO1xufVxuXG4uYm90dG9tLXJpZ2h0IHtcbiAgICB0ZXh0LXNoYWRvdzpcbiAgICAgICAgICAgIC0xcHggLTFweCAwICMwMDAsXG4gICAgICAgICAgICAxcHggLTFweCAwICMwMDAsXG4gICAgICAgICAgICAtMXB4IDFweCAwICMwMDAsXG4gICAgICAgICAgICAxcHggMXB4IDAgIzAwMDtcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XG4gICAgYm90dG9tOiA4cHg7XG4gICAgcmlnaHQ6IDE2cHg7XG4gICAgY29sb3I6IGdyZWVuO1xufVxuXG4udG9wLXJpZ2h0IHtcbiAgICB0ZXh0LXNoYWRvdzpcbiAgICAgICAgICAgIC0xcHggLTFweCAwICMwMDAsXG4gICAgICAgICAgICAxcHggLTFweCAwICMwMDAsXG4gICAgICAgICAgICAtMXB4IDFweCAwICMwMDAsXG4gICAgICAgICAgICAxcHggMXB4IDAgIzAwMDtcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XG4gICAgdG9wOiA4cHg7XG4gICAgcmlnaHQ6IDE2cHg7XG4gICAgY29sb3I6IGRvZGdlcmJsdWU7XG59XG5cbi5jYXJkIHtcbiAgICAgbWFyZ2luLWxlZnQ6IDF2dztcbiAgICAgZGlzcGxheTppbmxpbmUtYmxvY2s7XG4gICAgIGhlaWdodDogMTJ2aDtcbiAgICAgdmVydGljYWwtYWxpZ246IHRvcDtcbn1cblxuLnRhcmdldERldGFpbHMge1xuICAgIG1hcmdpbi1sZWZ0OiAxdnc7XG4gICAgZGlzcGxheTppbmxpbmUtYmxvY2s7XG4gICAgaGVpZ2h0OiAxMnZoO1xuICAgIHZlcnRpY2FsLWFsaWduOiB0b3A7XG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xufVxuXG4uY2FyZDpob3ZlciB7XG4gICAgYm94LXNoYWRvdzogMCA4cHggMTZweCAwIHJnYmEoMCwwLDAsMC4yKTtcbn1cblxuLmNvbnRDYXJkIHtcbiAgICB3b3JkLWJyZWFrOiBicmVhay1hbGw7XG4gICAgZm9udC1zaXplOiAxdnc7XG4gICAgcGFkZGluZy1yaWdodDogMDtcbiAgICBwYWRkaW5nLWxlZnQ6IDA7XG4gICAgbWFyZ2luLXJpZ2h0OiBhdXRvO1xuICAgIG1hcmdpbi1sZWZ0OiBhdXRvO1xufVxuXG4vKiBDbGVhcmZpeCAoY2xlYXIgZmxvYXRzKSAqL1xuLnJvd3tcbiAgICBtYXJnaW46IDAgYXV0bztcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gICAgaGVpZ2h0OiAxNHZoO1xufVxuXG4jdGhlQm9hcmR7XG4gICAgZGlzcGxheTogbm9uZTtcbiAgICBoZWlnaHQ6IDg0dmg7XG59XG5cbi50YXVudCB7XG4gICAgbWFyZ2luLWxlZnQ6IDF2dztcbiAgICBib3JkZXItdG9wOiBzb2xpZCAjMDBmZjI0O1xuICAgIGhlaWdodDogMTJ2aDtcbn1cblxuLmNhbkF0dGFjayB7XG4gICAgbWFyZ2luLWxlZnQ6IDF2dztcbiAgICBib3JkZXItcmlnaHQ6IHNvbGlkICNmZjAwMDA7XG4gICAgaGVpZ2h0OiAxMnZoO1xufVxuXG4ubGlmZVN0ZWFsIHtcbiAgICBtYXJnaW4tbGVmdDogMXZ3O1xuICAgIGJvcmRlci1sZWZ0OiBzb2xpZCAjZmZlZjAwO1xuICAgIGhlaWdodDogMTJ2aDtcbn1cblxuLmhlcm9wb3dlciB7XG4gICAgaGVpZ2h0OiA4dmg7XG4gICAgdmVydGljYWwtYWxpZ246IG1pZGRsZTtcbn1cblxuIl19 */"

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!DOCTYPE html>\n<html>\n\n<head>\n    <meta charset=\"utf-8\">\n    <title>Spring Boot WebSocket</title>\n    <base href=\"/\">\n\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n</head>\n\n<body>\n<div id=\"theBoard\" style=\"display: none\">\n\n    <div id=\"parentHisHero\" class=\"row\">\n        <div class=\"card\">\n            <p style=\"display: inline-block\" >Mana: </p> <b > {{hisMana?.currentMana}} / {{hisMana?.manaMax}} </b>\n            <a class=\"card\" >\n                <img class=\"card\" src= {{hisHero?.imgurl}} alt=\"Avatar\">\n            </a>\n            <a *ngIf=\"hisHero?.canUseHeroPower\" class=\"card\"  style=\"display: inline-block\" (click)=\"openHeroPowerPopup()\">\n                <img class=\"heropower\"  src= {{hisHero?.powerImgUrl}} alt=\"Avatar\">\n            </a>\n            <div *ngIf=\"hisHero?.canUseHeroPower == false\" class = \"card\">\n                <img class=\"heropower\" src= \"https://i.imgur.com/YB4MdzJ.jpg\" alt=\"Avatar\">\n            </div>\n            <ul style=\"display: inline-block\">\n                <li>Points de vie:  <b> {{hisHero?.currentHealthPoints}} </b> </li>\n                <li *ngIf=\"hisHero?.armorPoints>0\">Armure: <b>{{hisHero?.armorPoints}}</b></li>\n            </ul>\n        </div>\n    </div>\n\n    <div id=\"parentHisHand\" class=\"row\">\n        <a class=\"card\" *ngFor=\"let hisCard of hisHand\" >\n            <img class=\"card\" src= \"https://i.imgur.com/AotB9OX.png\" alt=\"Avatar\">\n        </a>\n    </div>\n\n    <div id=\"parentHisMinions\" class=\"row\">\n        <div class=\"card\" *ngFor=\"let myCard of hisMinions\">\n            <a (click)=\"openCardPopup(myCard.id)\" >\n                <div class=\"cardImgContainer\">\n                    <img [ngClass]=\"{\n                        'card': !myCard.canAttack && !myCard.taunt && !myCard.lifeSteal,\n                        'taunt': myCard.taunt,\n                        'canAttack':  myCard.canAttack && myCard.damagePoints>0,\n                        'lifeSteal': myCard.lifeSteal}\"\n                         src= {{myCard.imgurl}} alt=\"Avatar\">\n                    <div class=\"bottom-left\">{{myCard.damagePoints}}</div>\n                    <div class=\"bottom-right\">{{myCard.healthPoints}}</div>\n                </div>\n            </a >\n\n            <div class=\"form-popup\"  [attr.id]=\"'cardPopup' + myCard.id\" style=\"display: none\">\n                <form class=\"form-container\">\n                    <button type=\"submit\" class=\"btn cancel\" (click)=\"closeCardPopup(myCard.id)\">Fermer</button>\n                    <img class=\"card\" src={{myCard.imgurl}} alt=\"Avatar\">\n                    <div class=\"containerCardDetails\">\n                        <h4><b>{{myCard.name}}</b></h4>\n                        <p>Cout: {{myCard.requiredMana}}</p>\n                        <p>Dégats: <b>{{myCard.damagePoints}}</b></p>\n                        <p>Vie: <b>{{myCard.healthPoints}}</b></p>\n                        <p>{{myCard.text}}</p>\n                    </div>\n                </form>\n            </div>\n        </div>\n    </div>\n\n    <div id=\"parentMyMinions\" class=\"row\">\n        <div class=\"card\" *ngFor=\"let myCard of myMinions\">\n            <a (click)=\"openCardPopup(myCard.id)\" >\n                <div class=\"cardImgContainer\">\n                    <img [ngClass]=\"{\n                            'card': !myCard.canAttack && !myCard.taunt && !myCard.lifeSteal,\n                            'taunt': myCard.taunt,\n                            'canAttack': myCard.canAttack && myCard.damagePoints>0,\n                            'lifeSteal': myCard.lifeSteal}\"\n                         src= {{myCard.imgurl}} alt=\"Avatar\">\n                    <div class=\"bottom-left\">{{myCard.damagePoints}}</div>\n                    <div class=\"bottom-right\">{{myCard.healthPoints}}</div>\n                </div>\n            </a >\n\n            <div class=\"form-popup\"  [attr.id]=\"'cardPopup' + myCard.id\" style=\"display: none\">\n                <form class=\"form-container\">\n                    <button *ngIf=\"myCard.canAttack && myCard.damagePoints>0\" class=\"btn btn-default\" type=\"button\" (click)=\"showTargetForMinion(myCard.id)\">Attaquer</button>\n                    <button type=\"submit\" class=\"btn cancel\" (click)=\"closeCardPopup(myCard.id)\">Fermer</button>\n                    <img class=\"card\" src={{myCard.imgurl}} alt=\"Avatar\" >\n                    <div class=\"containerCardDetails\">\n                        <h4><b>{{myCard.name}}</b></h4>\n                        <p>Coût: {{myCard.requiredMana}}</p>\n                        <p>Dégats: <b>{{myCard.damagePoints }}</b></p>\n                        <p>Vie: <b>{{myCard.healthPoints}}</b></p>\n                        <p>{{myCard.text}}</p>\n                    </div>\n                </form>\n            </div>\n        </div>\n\n    </div>\n\n    <div id=\"parentMyHand\" class=\"row\">\n        <div class=\"card\" *ngFor=\"let myCard of myCards\">\n            <a (click)=\"openCardPopup(myCard.id)\" >\n                <div class=\"cardImgContainer\">\n                    <img class=\"card\" src= {{myCard.imgurl}} alt=\"Avatar\">\n                    <div *ngIf=\"myCard.cardType === 'minion'\" class=\"bottom-left\">{{myCard.damagePoints}}</div>\n                    <div *ngIf=\"myCard.cardType === 'minion'\" class=\"bottom-right\">{{myCard.healthPoints}}</div>\n                    <div class=\"top-right\">{{myCard.requiredMana}}</div>\n                </div>\n\n            </a >\n\n            <div class=\"form-popup\"  [attr.id]=\"'cardPopup' + myCard.id\" style=\"display: none\">\n                <form class=\"form-container\">\n                    <button type=\"submit\" class=\"btn cancel\" (click)=\"closeCardPopup(myCard.id)\">Fermer</button>\n                    <div *ngIf=\"myCard.requiredMana <= myMana?.currentMana\">\n                        <div *ngIf=\"myCard.cardType==='minion'\">\n                            <button class=\"btn btn-default\" type=\"button\" (click)=\"playMinion(myCard.id)\">Invoquer</button>\n                        </div>\n                        <div *ngIf=\"myCard.cardType==='spell'\">\n                            <button *ngIf=\"myCard.targetSpell==false\" class=\"btn btn-default\" type=\"button\" (click)=\"playSpell(myCard.id)\">Lancer le sort</button>\n                            <button *ngIf=\"myCard.targetSpell\" class=\"btn btn-default\" type=\"button\" (click)=\"openPromptSpellTarget(myCard.id)\">Choisir une cible</button>\n                        </div>\n                    </div>\n                    <img class=\"card\" src={{myCard.imgurl}} alt=\"Avatar\">\n                    <div class=\"containerCardDetails\">\n                        <h4><b>{{myCard.name}}</b></h4>\n                        <p>Coût: {{myCard.requiredMana}}</p>\n                        <div *ngIf=\"myCard.cardType === 'minion' \">\n                            <p>Dégat: <b>{{myCard.damagePoints}}</b></p>\n                            <p>Vie: <b>{{myCard.healthPoints}}</b></p>\n                        </div>\n                        <p>{{myCard.text}}</p>\n                    </div>\n\n                </form>\n            </div>\n        </div>\n    </div>\n    <div id=\"parentMyHero\" class=\"row\">\n        <div>\n            <button class=\"btn btn-default\" type=\"button\" (click)=\"giveUp()\" style=\"display: inline-block\">Abandonner</button>\n            <button id=\"passTurnBtn\" class=\"btn cancel\" style=\"display: none\" (click)=\"passTurn()\">Passer son tour</button>\n        </div>\n        <div>\n            <ul style=\"display: inline-block\">\n                <li>Mana: <b > {{myMana?.currentMana}} / {{myMana?.manaMax}} </b> </li>\n                <li>Points de vie:  <b> {{myHero?.currentHealthPoints}} </b> </li>\n                <li *ngIf=\"myHero?.armorPoints>0\">Armure: <b>{{myHero?.armorPoints}}</b></li>\n                <li>Aura : {{myMana?.aura}}</li>\n            </ul>\n            <a class=\"card\"  >\n                <img class=\"card\" class=\"card\" src= {{myHero?.imgurl}} alt=\"Avatar\">\n            </a>\n            <a *ngIf=\"myHero?.canUseHeroPower\" class=\"card\"  style=\"display: inline-block\" (click)=\"openHeroPowerPopup()\">\n                <img class=\"heropower\" src= {{myHero?.powerImgUrl}} alt=\"Avatar\">\n            </a>\n            <div *ngIf=\"myHero?.canUseHeroPower == false\" class = \"card\">\n                <img class=\"heropower\"  src= \"https://i.imgur.com/YB4MdzJ.jpg\" alt=\"Avatar\">\n            </div>\n\n            <div class=\"form-popup\" id=\"heroPowerPopup\" style=\"display: none\">\n                <form class=\"form-container\">\n                    <button type=\"submit\" class=\"btn cancel\" (click)=\"closeHeroPowerPopup()\">Fermer</button>\n                    <div>\n                        <div *ngIf=\"myHero?.targetedHeroPower\">\n                            <button class=\"btn btn-default\" type=\"button\" (click)=\"openPromptHeroPowerTarget()\">Choisir une cible</button>\n                        </div>\n                        <div *ngIf=\"myHero?.targetedHeroPower == false\">\n                            <button class=\"btn btn-default\" type=\"button\" (click)=\"useHeroPower()\">Lancer le pouvoir héroïque</button>\n                        </div>\n                    </div>\n                    <img class=\"card\" src={{myHero?.powerImgUrl}} alt=\"Avatar\" >\n                    <div class=\"containerCardDetails\">\n                        <h4><b>{{myHero?.powerImgName}}</b></h4>\n                        <p>Coût: <b>2</b></p>\n                        <p>{{myHero?.powerImgText}}</p>\n                    </div>\n\n                </form>\n            </div>\n\n        </div>\n    </div>\n\n</div>\n<div style=\"color: #000a6a; text-align: center; height: 16vh\">\n    <h1>{{title}}</h1>\n    <form class=\"form-inline\">\n        <div class=\"form-group\">\n            <label for=\"connect\">Connexion au serveur:</label>\n            <button id=\"connect\" class=\"btn btn-default\" type=\"button\" [disabled]=\"!disabled\" (click)=\"connect()\">Connexion</button>\n            <button id=\"disconnect\" class=\"btn btn-default\" type=\"submit\" [disabled]=\"disabled\" (click)=\"disconnect()\">Déconnexion</button>\n        </div>\n    </form>\n</div>\n<div id=\"gameSearch\" style=\"display: none\">\n    <div>\n        <button id=\"connectToGame\" class=\"btn btn-default\" type=\"button\" (click)=\"openHeroSelect()\">Trouver une partie</button>\n    </div>\n    <div>\n        <button id=\"cancelSearch\" class=\"btn btn-default hiddenCancelButton\" type=\"button\" (click)=\"cancelSearch()\">Annuler</button>\n    </div>\n    <div class=\"form-popup\" id=\"heroSelectPop\">\n        <form class=\"form-container\">\n            <h1>Choix du héro</h1>\n            <select name=\"heroName\" (change)=\"selectHeroHandler($event)\">\n                <option value=\"Jaina\">Jaina (mage)</option>\n                <option value=\"Garrosh\">Garrosh (guerrier)</option>\n                <option value=\"Uther\">Uther (paladin)</option>\n            </select>\n            <button id=\"findGame\" class=\"btn btn-default\" type=\"button\" (click)=\"connectToGame()\">Trouver une partie</button>\n            <button type=\"submit\" class=\"btn cancel\" (click)=\"closeHeroSelect()\">Annuler</button>\n        </form>\n    </div>\n</div>\n<div id=\"messageServer\">\n    <table id=\"conversation\" class=\"table table-striped\" style=\"margin-top: 20px;\">\n        <thead>\n        <tr>\n            <th>Messages serveur:</th>\n        </tr>\n        </thead>\n        <tbody *ngFor=\"let greeting of greetings\">\n        <tr>\n            <td>{{greeting}}</td>\n        </tr>\n        </tbody>\n    </table>\n</div>\n\n<div class=\"form-popup\"  id=\"cardPopupShowTarget\" style=\"display: none; z-index: 50\">\n\n    <form class=\"form-container\">\n        <button type=\"submit\" class=\"btn cancel\" (click)=\"closeTargetPopup()\">Fermer</button>\n        <button class=\"btn btn-default\" type=\"button\" (click)=\"openTargetPopup()\">Attaquer un serviteur</button>\n        <button *ngIf=\"canAttackHero\" class=\"btn btn-default\" type=\"button\" (click)=\"attackHero()\">Attaquer le héros</button>\n\n        <div id=\"targetDetails\" class=\"row\" style=\"display: none\">\n            <div class=\"targetDetails\" *ngFor=\"let myTarget of myTargets\">\n                <div id=\"targetImage\" class=\"card\">\n                    <a (click)=\"openTargetDetails(myTarget.id)\" >\n                        <div class=\"cardImgContainer\">\n                            <img [ngClass]=\"{\n                                        'card': !myTarget.canAttack && !myTarget.taunt && !myTarget.lifeSteal,\n                                        'taunt': myTarget.taunt,\n                                        'canAttack': myTarget.canAttack,\n                                        'lifeSteal': myTarget.lifeSteal}\"\n                                 src= {{myTarget.imgurl}} alt=\"Avatar\">\n                            <div class=\"bottom-left\">{{myTarget.damagePoints}}</div>\n                            <div class=\"bottom-right\">{{myTarget.healthPoints}}</div>\n                        </div>\n\n                    </a >\n                </div>\n\n                <div [attr.id]=\"'targetDetailsPopup' + myTarget.id\" style=\"position: relative; z-index: 100; display: none\">\n                    <div class=\"form-popup\" style=\"display: block\">\n                        <form class=\"form-container\">\n                            <button type=\"submit\" class=\"btn cancel\" (click)=\"closeTargetDetails(myTarget.id)\">Fermer</button>\n                            <button class=\"btn btn-default\" type=\"button\" (click)=\"attackThisMinion( myTarget.id)\">Attaquer ce serviteur</button>\n                            <img class=\"card\" src={{myTarget.imgurl}} alt=\"Avatar\" >\n                            <div class=\"containerCardDetails\">\n                                <h4><b>{{myTarget.name}}</b></h4>\n                                <p>Coût: {{myTarget.requiredMana}}</p>\n                                <p>Dégats: <b>{{myTarget.damagePoints}}</b></p>\n                                <p>Vie: <b>{{myTarget.healthPoints}}</b></p>\n                                <p>{{myTarget.text}}</p>\n                            </div>\n                        </form>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </form>\n</div>\n<div class=\"form-popup\" id=\"yourTurnPop\" style=\"display: none\">\n    <form class=\"form-container\">\n        <button type=\"submit\" class=\"btn cancel\" (click)=\"closeYourTurnPopup()\">Fermer</button>\n        <div class=\"containerCardDetails\" id=\"yourTurnPopMessage\">\n        </div>\n    </form>\n</div>\n<div class=\"form-popup\"  id=\"spellPopupShowTarget\" style=\"display: none; z-index: 50\">\n\n    <form class=\"form-container\">\n        <button type=\"submit\" class=\"btn cancel\" (click)=\"closeTargetSpellPopup()\">Fermer</button>\n        <button class=\"btn btn-default\" type=\"button\" (click)=\"showAlliedTargets()\">Cibler un allié</button>\n        <button class=\"btn btn-default\" type=\"button\" (click)=\"showEnemiesTargets()\">Cibler un ennemi</button>\n\n        <div id=\"targetSpellDetails\" class=\"row\" style=\"display: none\">\n            <div class=\"targetDetails\" *ngFor=\"let myTarget of spellTargets\">\n                <div id=\"targetSpellImage\" class=\"card\">\n                    <a (click)=\"openTargetDetails(myTarget.id)\" >\n                        <div class=\"cardImgContainer\">\n                            <img [ngClass]=\"{\n                                        'card': !myTarget.canAttack && !myTarget.taunt && !myTarget.lifeSteal,\n                                        'taunt': myTarget.taunt,\n                                        'canAttack': myTarget.canAttack,\n                                        'lifeSteal': myTarget.lifeSteal}\"\n                                 src= {{myTarget.imgurl}} alt=\"Avatar\">\n                            <div class=\"bottom-left\">{{myTarget.damagePoints}}</div>\n                            <div class=\"bottom-right\">{{myTarget.healthPoints}}</div>\n                        </div>\n\n                    </a >\n                </div>\n\n                <div [attr.id]=\"'targetDetailsPopup' + myTarget.id\" style=\"position: relative; z-index: 100; display: none\">\n                    <div class=\"form-popup\" style=\"display: block\">\n                        <form class=\"form-container\">\n                            <button type=\"submit\" class=\"btn cancel\" (click)=\"closeTargetDetails(myTarget.id)\">Fermer</button>\n                            <button class=\"btn btn-default\" type=\"button\" (click)=\"castSpellOnTarget( myTarget.id)\">Cibler ce serviteur</button>\n                            <img class=\"card\" src={{myTarget.imgurl}} alt=\"Avatar\" >\n                            <div class=\"containerCardDetails\">\n                                <h4><b>{{myTarget.name}}</b></h4>\n                                <p>Coût: {{myTarget.requiredMana}}</p>\n                                <p>Dégats: <b>{{myTarget.damagePoints}}</b></p>\n                                <p>Vie: <b>{{myTarget.healthPoints}}</b></p>\n                                <p>{{myTarget.text}}</p>\n                            </div>\n                        </form>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </form>\n</div>\n<div class=\"form-popup\"  id=\"heroPowerPopupShowTarget\" style=\"display: none; z-index: 50\">\n\n    <form class=\"form-container\">\n        <button type=\"submit\" class=\"btn cancel\" (click)=\"closeTargetHeroPowerPopup()\">Fermer</button>\n        <button class=\"btn btn-default\" type=\"button\" (click)=\"castHeroPowerOnHero()\">Cibler le héros ennemi</button>\n        <button class=\"btn btn-default\" type=\"button\" (click)=\"openHeroPowerTargetList()\">Cibler un serviteur ennemi</button>\n        <div id=\"targetHeroPowerDetails\" class=\"row\" style=\"display: none\">\n            <div class=\"targetDetails\" *ngFor=\"let myTarget of hisMinions\">\n                <div id=\"targetHeroPowerImage\" class=\"card\">\n                    <a (click)=\"openTargetDetails(myTarget.id)\" >\n                        <div class=\"cardImgContainer\">\n                            <img [ngClass]=\"{\n                                        'card': !myTarget.canAttack && !myTarget.taunt && !myTarget.lifeSteal,\n                                        'taunt': myTarget.taunt,\n                                        'canAttack': myTarget.canAttack,\n                                        'lifeSteal': myTarget.lifeSteal}\"\n                                 src= {{myTarget.imgurl}} alt=\"Avatar\">\n                            <div class=\"bottom-left\">{{myTarget.damagePoints}}</div>\n                            <div class=\"bottom-right\">{{myTarget.healthPoints}}</div>\n                        </div>\n                    </a >\n                </div>\n\n                <div [attr.id]=\"'targetDetailsPopup' + myTarget.id\" style=\"position: relative; z-index: 100; display: none\">\n                    <div class=\"form-popup\" style=\"display: block\">\n                        <form class=\"form-container\">\n                            <button type=\"submit\" class=\"btn cancel\" (click)=\"closeTargetDetails(myTarget.id)\">Fermer</button>\n                            <button class=\"btn btn-default\" type=\"button\" (click)=\"castHeroPowerOnTarget( myTarget.id)\">Cibler ce serviteur</button>\n                            <img class=\"card\" src={{myTarget.imgurl}} alt=\"Avatar\" >\n                            <div class=\"containerCardDetails\">\n                                <h4><b>{{myTarget.name}}</b></h4>\n                                <p>Coût: {{myTarget.requiredMana}}</p>\n                                <p>Dégats: <b>{{myTarget.damagePoints}}</b></p>\n                                <p>Vie: <b>{{myTarget.healthPoints}}</b></p>\n                                <p>{{myTarget.text}}</p>\n                            </div>\n                        </form>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </form>\n</div>\n\n</body>\n\n</html>\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _stomp_stompjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @stomp/stompjs */ "./node_modules/@stomp/stompjs/bundles/stomp.umd.js");
/* harmony import */ var _stomp_stompjs__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_stomp_stompjs__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var sockjs_client__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! sockjs-client */ "./node_modules/sockjs-client/lib/entry.js");
/* harmony import */ var sockjs_client__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(sockjs_client__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _game_game_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./game/game.component */ "./src/app/game/game.component.ts");





var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'mini Hearthstone';
        this.game = new _game_game_component__WEBPACK_IMPORTED_MODULE_4__["GameComponent"]();
        this.greetings = [];
        this.myMinions = [];
        this.myTargets = [];
        this.hisMinions = [];
        this.myCards = [];
        this.hisHand = [];
        this.disabled = true;
        this.canAttackHero = true;
        this.selectedHero = 'Jaina';
        this.spellTargets = [];
        this.openedPopup = null;
        this.stompClient = null;
    }
    AppComponent.prototype.setConnected = function (connected) {
        this.disabled = !connected;
        if (connected) {
            this.greetings = [];
        }
    };
    AppComponent.prototype.connect = function () {
        var socket = new sockjs_client__WEBPACK_IMPORTED_MODULE_3__('/endpoint');
        this.stompClient = _stomp_stompjs__WEBPACK_IMPORTED_MODULE_2__["Stomp"].over(socket);
        var _this = this;
        this.stompClient.connect({}, function (frame) {
            _this.setConnected(true);
            console.log('Connected: ' + frame);
            _this.showGreeting("Vous êtes bien connecté au serveur du mini-hearthstone");
            _this.stompClient.subscribe('/user/queue/reply', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showGreeting(JSON.parse(resp.body).greeting);
            });
            _this.stompClient.subscribe('/user/queue/reply_myHand', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showHand(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_gameFound', function (resp) {
                console.log("server answer: " + resp.body);
                _this.gameReady();
                _this.showGreeting(JSON.parse(resp.body).greeting);
            });
            _this.stompClient.subscribe('/user/queue/reply_hisHand', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showHisHand(JSON.parse(resp.body).nbrCards);
            });
            _this.stompClient.subscribe('/user/queue/reply_myHero', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showMyHero(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_hisHero', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showHisHero(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_myMana', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showMyMana(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_hisMana', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showHisMana(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_yourTurn', function (resp) {
                document.getElementById("passTurnBtn").style.display = "inline-block";
                console.log("server answer: " + resp.body);
                _this.openYourTurnPopup(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_passedTurn', function (resp) {
                document.getElementById("passTurnBtn").style.display = "none";
                console.log("server answer: " + resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_gameOver', function (resp) {
                console.log("server answer: " + resp.body);
                _this.gameOver(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_playMinion', function (resp) {
                console.log("server answer: " + resp.body);
                _this.showMinions(resp.body);
            });
            _this.stompClient.subscribe('/user/queue/reply_playedMinion', function (resp) {
                console.log("Votre adversaire a joué: " + resp.body);
                _this.showHisMinions(resp.body);
                var hasTaunt = false;
                for (var _i = 0, _a = _this.hisMinions; _i < _a.length; _i++) {
                    var minion = _a[_i];
                    if (minion.taunt) {
                        hasTaunt = true;
                    }
                }
                if (hasTaunt) {
                    _this.canAttackHero = false;
                }
                else {
                    _this.canAttackHero = true;
                }
            });
            _this.stompClient.subscribe('/user/queue/reply_targetsMinions', function (resp) {
                console.log("La liste de vos cibles: " + resp.body);
                _this.showTargets(resp.body);
            });
        });
        document.getElementById("gameSearch").style.display = "block";
    };
    AppComponent.prototype.disconnect = function () {
        document.getElementById("gameSearch").style.display = "none";
        document.getElementById("theBoard").style.display = "none";
        this.cancelSearch();
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
        this.setConnected(false);
        console.log('Disconnected!');
        this.showGreeting("Au revoir!");
    };
    AppComponent.prototype.gameReady = function () {
        document.getElementById("cancelSearch").style.display = "none";
        document.getElementById("theBoard").style.display = "block";
    };
    AppComponent.prototype.passTurn = function () {
        this.stompClient.send('/passTurn', {});
    };
    AppComponent.prototype.giveUp = function () {
        this.stompClient.send('/gameOver', {});
    };
    AppComponent.prototype.gameOver = function (resp) {
        document.getElementById("passTurnBtn").style.display = "none";
        document.getElementById("yourTurnPop").style.display = "block";
        document.getElementById("yourTurnPopMessage").innerHTML = "<h4><b>" + resp + "</b></h4>";
        document.getElementById("theBoard").style.display = "none";
        document.getElementById("connectToGame").style.display = "block";
        this.myCards = [];
        this.hisHand = [];
        this.myHero = null;
        this.hisHero = null;
        this.myMinions = [];
        this.hisMinions = [];
        this.myTargets = [];
    };
    AppComponent.prototype.openYourTurnPopup = function (resp) {
        document.getElementById("yourTurnPop").style.display = "none";
        document.getElementById("yourTurnPopMessage").innerHTML = resp;
        document.getElementById("yourTurnPop").style.display = "block";
    };
    AppComponent.prototype.openPromptSpellTarget = function (idCard) {
        document.getElementById("spellPopupShowTarget").style.display = "block";
        this.spellThatAttackId = idCard;
    };
    AppComponent.prototype.closeTargetSpellPopup = function () {
        document.getElementById("spellPopupShowTarget").style.display = "none";
    };
    AppComponent.prototype.showAlliedTargets = function () {
        this.spellTargets = this.myMinions;
        document.getElementById("targetSpellDetails").style.display = "block";
    };
    AppComponent.prototype.showEnemiesTargets = function () {
        this.spellTargets = this.hisMinions;
        document.getElementById("targetSpellDetails").style.display = "block";
    };
    AppComponent.prototype.closeYourTurnPopup = function () {
        document.getElementById("yourTurnPop").style.display = "none";
    };
    AppComponent.prototype.connectToGame = function () {
        this.closeHeroSelect();
        document.getElementById("connectToGame").style.display = "none";
        document.getElementById("cancelSearch").style.display = "block";
        this.stompClient.send('/connectGame', {}, this.selectedHero);
    };
    AppComponent.prototype.showGreeting = function (message) {
        this.greetings.push(message);
    };
    AppComponent.prototype.showMyHero = function (message) {
        console.log('message brut: ' + message);
        this.myHero = JSON.parse(message);
    };
    AppComponent.prototype.showHisHero = function (message) {
        console.log('message brut: ' + message);
        this.hisHero = JSON.parse(message);
    };
    AppComponent.prototype.showHand = function (message) {
        console.log('message brut: ' + message);
        var parsed = JSON.parse(message);
        console.log('parsed: ' + parsed);
        this.myCards = parsed;
        console.log("taille du tableau: " + this.myCards.length);
    };
    AppComponent.prototype.showMinions = function (message) {
        console.log('message brut de mes minions: ' + message);
        var parsed = JSON.parse(message);
        console.log('parsed mes minions: ' + parsed);
        this.myMinions = parsed;
    };
    AppComponent.prototype.showHisMinions = function (message) {
        console.log('message brut de ses minions: ' + message);
        var parsed = JSON.parse(message);
        console.log('parsed ses minions: ' + parsed);
        this.hisMinions = parsed;
    };
    AppComponent.prototype.showMyMana = function (message) {
        console.log('message brut: ' + message);
        this.myMana = JSON.parse(message);
    };
    AppComponent.prototype.showHisMana = function (message) {
        console.log('message brut: ' + message);
        this.hisMana = JSON.parse(message);
    };
    AppComponent.prototype.showHisHand = function (nbrCards) {
        this.hisHand = new Array(nbrCards);
    };
    AppComponent.prototype.openHeroSelect = function () {
        document.getElementById("heroSelectPop").style.display = "block";
    };
    AppComponent.prototype.closeHeroSelect = function () {
        document.getElementById("heroSelectPop").style.display = "none";
    };
    AppComponent.prototype.selectHeroHandler = function (event) {
        this.selectedHero = event.target.value;
    };
    AppComponent.prototype.cancelSearch = function () {
        this.stompClient.send('/disconnectGame', {});
        document.getElementById("cancelSearch").style.display = "none";
        document.getElementById("connectToGame").style.display = "block";
    };
    AppComponent.prototype.openCardPopup = function (id) {
        if (this.openedPopup != null) {
            document.getElementById("cardPopup" + this.openedPopup).style.display = "none";
        }
        document.getElementById("cardPopup" + id).style.display = "block";
        this.openedPopup = id;
    };
    AppComponent.prototype.closeCardPopup = function (id) {
        document.getElementById("cardPopup" + id).style.display = "none";
        this.openedPopup = null;
    };
    AppComponent.prototype.playMinion = function (id) {
        this.stompClient.send('/playMinion', {}, id);
        this.closeCardPopup(id);
    };
    AppComponent.prototype.playSpell = function (id) {
        this.stompClient.send('/playSpell', {}, id);
        this.closeCardPopup(id);
    };
    AppComponent.prototype.showTargetForMinion = function (id) {
        this.minionThatAttackId = id;
        this.stompClient.send('/showTargetForMinion', {}, id);
        this.closeCardPopup(id);
    };
    AppComponent.prototype.showTargets = function (message) {
        this.myTargets = JSON.parse(message);
        document.getElementById("cardPopupShowTarget").style.display = "block";
    };
    AppComponent.prototype.closeTargetPopup = function () {
        document.getElementById("cardPopupShowTarget").style.display = "none";
        document.getElementById("targetDetails").style.display = "none";
        this.myTargets = [];
    };
    AppComponent.prototype.openTargetPopup = function () {
        document.getElementById("targetDetails").style.display = "block";
    };
    AppComponent.prototype.openTargetDetails = function (targetID) {
        document.getElementById("targetDetailsPopup" + targetID).style.display = "";
    };
    AppComponent.prototype.closeTargetDetails = function (targetID) {
        document.getElementById("targetDetailsPopup" + targetID).style.display = "none";
    };
    AppComponent.prototype.attackThisMinion = function (targetID) {
        console.log("Id de la carte qui attaque  " + this.minionThatAttackId);
        console.log("Id de la carte qui est attaquée  " + targetID);
        var message = { attackerID: this.minionThatAttackId, targetID: targetID };
        this.stompClient.send('/attackThisMinion', {}, JSON.stringify(message));
        this.closeTargetDetails(targetID);
        this.closeTargetPopup();
        this.myTargets = [];
    };
    AppComponent.prototype.castSpellOnTarget = function (targetID) {
        console.log("Id du qui attaque  " + this.spellThatAttackId);
        console.log("Id de la carte qui est attaquée  " + targetID);
        this.closeCardPopup(this.spellThatAttackId);
        var message = { spellID: this.spellThatAttackId, targetID: targetID };
        this.stompClient.send('/castSpellOnThisMinion', {}, JSON.stringify(message));
        document.getElementById("targetHeroPowerDetails").style.display = "none";
        this.closeTargetDetails(targetID);
        this.closeTargetSpellPopup();
        this.myTargets = [];
    };
    AppComponent.prototype.attackHero = function () {
        this.stompClient.send('/attackHero', {}, this.minionThatAttackId);
        this.closeTargetPopup();
    };
    AppComponent.prototype.openHeroPowerPopup = function () {
        document.getElementById("heroPowerPopup").style.display = "block";
    };
    AppComponent.prototype.closeHeroPowerPopup = function () {
        document.getElementById("heroPowerPopup").style.display = "none";
    };
    AppComponent.prototype.closeTargetHeroPowerPopup = function () {
        document.getElementById("heroPowerPopupShowTarget").style.display = "none";
        document.getElementById("targetHeroPowerDetails").style.display = "none";
    };
    AppComponent.prototype.openPromptHeroPowerTarget = function () {
        document.getElementById("heroPowerPopupShowTarget").style.display = "block";
    };
    AppComponent.prototype.castHeroPowerOnTarget = function (targetID) {
        this.stompClient.send('/useHeroPowerOnTarget', {}, targetID);
        this.closeTargetHeroPowerPopup();
        this.closeHeroPowerPopup();
        this.myTargets = [];
    };
    AppComponent.prototype.useHeroPower = function () {
        this.stompClient.send('/useHeroPower', {});
        this.closeHeroPowerPopup();
    };
    AppComponent.prototype.castHeroPowerOnHero = function () {
        this.stompClient.send('/useHeroPowerOnTarget', {}, "hero");
        this.closeTargetHeroPowerPopup();
        this.closeHeroPowerPopup();
    };
    AppComponent.prototype.openHeroPowerTargetList = function () {
        document.getElementById("targetHeroPowerDetails").style.display = "block";
    };
    AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _welcome_welcome_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./welcome/welcome.component */ "./src/app/welcome/welcome.component.ts");
/* harmony import */ var _page_not_found_page_not_found_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./page-not-found/page-not-found.component */ "./src/app/page-not-found/page-not-found.component.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _hero_hero_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./hero/hero.component */ "./src/app/hero/hero.component.ts");
/* harmony import */ var _card_card_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./card/card.component */ "./src/app/card/card.component.ts");
/* harmony import */ var _mana_mana_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./mana/mana.component */ "./src/app/mana/mana.component.ts");











var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"],
                _welcome_welcome_component__WEBPACK_IMPORTED_MODULE_5__["WelcomeComponent"],
                _page_not_found_page_not_found_component__WEBPACK_IMPORTED_MODULE_6__["PageNotFoundComponent"],
                _hero_hero_component__WEBPACK_IMPORTED_MODULE_8__["HeroComponent"],
                _card_card_component__WEBPACK_IMPORTED_MODULE_9__["CardComponent"],
                _mana_mana_component__WEBPACK_IMPORTED_MODULE_10__["ManaComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_4__["AppRoutingModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_7__["FormsModule"]
            ],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/card/card.component.css":
/*!*****************************************!*\
  !*** ./src/app/card/card.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NhcmQvY2FyZC5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/card/card.component.html":
/*!******************************************!*\
  !*** ./src/app/card/card.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  card works!\n</p>\n"

/***/ }),

/***/ "./src/app/card/card.component.ts":
/*!****************************************!*\
  !*** ./src/app/card/card.component.ts ***!
  \****************************************/
/*! exports provided: CardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CardComponent", function() { return CardComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var CardComponent = /** @class */ (function () {
    function CardComponent() {
    }
    CardComponent.prototype.ngOnInit = function () {
    };
    CardComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-card',
            template: __webpack_require__(/*! ./card.component.html */ "./src/app/card/card.component.html"),
            styles: [__webpack_require__(/*! ./card.component.css */ "./src/app/card/card.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], CardComponent);
    return CardComponent;
}());



/***/ }),

/***/ "./src/app/game/game.component.ts":
/*!****************************************!*\
  !*** ./src/app/game/game.component.ts ***!
  \****************************************/
/*! exports provided: GameComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GameComponent", function() { return GameComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var GameComponent = /** @class */ (function () {
    function GameComponent() {
    }
    GameComponent.prototype.ngOnInit = function () {
    };
    GameComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-game',
            template: ""
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], GameComponent);
    return GameComponent;
}());



/***/ }),

/***/ "./src/app/hero/hero.component.css":
/*!*****************************************!*\
  !*** ./src/app/hero/hero.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2hlcm8vaGVyby5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/hero/hero.component.html":
/*!******************************************!*\
  !*** ./src/app/hero/hero.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  hero works!\n</p>\n"

/***/ }),

/***/ "./src/app/hero/hero.component.ts":
/*!****************************************!*\
  !*** ./src/app/hero/hero.component.ts ***!
  \****************************************/
/*! exports provided: HeroComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HeroComponent", function() { return HeroComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var HeroComponent = /** @class */ (function () {
    function HeroComponent() {
    }
    HeroComponent.prototype.ngOnInit = function () {
    };
    HeroComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-hero',
            template: __webpack_require__(/*! ./hero.component.html */ "./src/app/hero/hero.component.html"),
            styles: [__webpack_require__(/*! ./hero.component.css */ "./src/app/hero/hero.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], HeroComponent);
    return HeroComponent;
}());



/***/ }),

/***/ "./src/app/mana/mana.component.css":
/*!*****************************************!*\
  !*** ./src/app/mana/mana.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL21hbmEvbWFuYS5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/mana/mana.component.html":
/*!******************************************!*\
  !*** ./src/app/mana/mana.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n    mana works!\n</p>\n"

/***/ }),

/***/ "./src/app/mana/mana.component.ts":
/*!****************************************!*\
  !*** ./src/app/mana/mana.component.ts ***!
  \****************************************/
/*! exports provided: ManaComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ManaComponent", function() { return ManaComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var ManaComponent = /** @class */ (function () {
    function ManaComponent() {
    }
    ManaComponent.prototype.ngOnInit = function () {
    };
    ManaComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-mana',
            template: __webpack_require__(/*! ./mana.component.html */ "./src/app/mana/mana.component.html"),
            styles: [__webpack_require__(/*! ./mana.component.css */ "./src/app/mana/mana.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], ManaComponent);
    return ManaComponent;
}());



/***/ }),

/***/ "./src/app/page-not-found/page-not-found.component.css":
/*!*************************************************************!*\
  !*** ./src/app/page-not-found/page-not-found.component.css ***!
  \*************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3BhZ2Utbm90LWZvdW5kL3BhZ2Utbm90LWZvdW5kLmNvbXBvbmVudC5jc3MifQ== */"

/***/ }),

/***/ "./src/app/page-not-found/page-not-found.component.html":
/*!**************************************************************!*\
  !*** ./src/app/page-not-found/page-not-found.component.html ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h2>Page not found</h2>\n"

/***/ }),

/***/ "./src/app/page-not-found/page-not-found.component.ts":
/*!************************************************************!*\
  !*** ./src/app/page-not-found/page-not-found.component.ts ***!
  \************************************************************/
/*! exports provided: PageNotFoundComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PageNotFoundComponent", function() { return PageNotFoundComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var PageNotFoundComponent = /** @class */ (function () {
    function PageNotFoundComponent() {
    }
    PageNotFoundComponent.prototype.ngOnInit = function () {
    };
    PageNotFoundComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-page-not-found',
            template: __webpack_require__(/*! ./page-not-found.component.html */ "./src/app/page-not-found/page-not-found.component.html"),
            styles: [__webpack_require__(/*! ./page-not-found.component.css */ "./src/app/page-not-found/page-not-found.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], PageNotFoundComponent);
    return PageNotFoundComponent;
}());



/***/ }),

/***/ "./src/app/welcome/welcome.component.css":
/*!***********************************************!*\
  !*** ./src/app/welcome/welcome.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3dlbGNvbWUvd2VsY29tZS5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/welcome/welcome.component.html":
/*!************************************************!*\
  !*** ./src/app/welcome/welcome.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "{{message}}\n<a routerLink=\"/waitForPlayer\">Jouer</a>\n"

/***/ }),

/***/ "./src/app/welcome/welcome.component.ts":
/*!**********************************************!*\
  !*** ./src/app/welcome/welcome.component.ts ***!
  \**********************************************/
/*! exports provided: WelcomeComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WelcomeComponent", function() { return WelcomeComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var WelcomeComponent = /** @class */ (function () {
    function WelcomeComponent() {
        this.message = "Bienvenue sur mini Hearthstone! Cliquez sur le bouton ci-dessous pour commencer à jouer!";
    }
    WelcomeComponent.prototype.ngOnInit = function () {
    };
    WelcomeComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-welcome',
            template: __webpack_require__(/*! ./welcome.component.html */ "./src/app/welcome/welcome.component.html"),
            styles: [__webpack_require__(/*! ./welcome.component.css */ "./src/app/welcome/welcome.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], WelcomeComponent);
    return WelcomeComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /home/raphael/Documents/m1ALMA/mini-hearthstone/mini-hearthStone/MHClient/mini-hearthstone/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map
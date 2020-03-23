export class UserAuthModel {

    constructor(
      public userId: string,
      public role: string,
      private rememberMe: boolean,
      private _token: string,
      private _tokenExpirationDate: Date
    ) {}
  
    get token() {
      if (!this._tokenExpirationDate || new Date() > this._tokenExpirationDate) {
        return null;
      }
      return this._token;
    }
  }
(self.webpackChunkchicken_feet_web=self.webpackChunkchicken_feet_web||[]).push([[272],{272:(e,t,s)=>{"use strict";s.r(t),s.d(t,{GamingPageModule:()=>v});var n=s(8583),i=s(665),o=s(3083),r=s(8136),a=s(1406),u=s(205),c=s(5917),g=s(9773),l=s(639),h=s(1841);let p=(()=>{class e{constructor(e){this.http=e,this.ok=a.Z.ok}getAccountJoinedGameId(){return this.http.get("accounts").pipe((0,g.zg)(e=>e.result<this.ok?(0,u._)(e.message):(0,c.of)(e.data)))}getGame(e){return this.http.get(`games/${e}`).pipe((0,g.zg)(e=>e.result<this.ok?(0,u._)(e.message):(0,c.of)(e.data)))}getGameAvailableBonuses(e){return this.http.get(`games/${e}/bonuses/available`).pipe((0,g.zg)(e=>e.result<this.ok?(0,u._)(e.message):(0,c.of)(e.data)))}postChooseBonusIndex(e,t){return this.http.post(`games/${e}/bonuses/${t}/choose`,null).pipe((0,g.zg)(e=>e.result<this.ok?(0,u._)(e.message):(0,c.of)(e.result)))}}return e.\u0275fac=function(t){return new(t||e)(l.LFG(h.eN))},e.\u0275prov=l.Yz7({token:e,factory:e.\u0275fac}),e})();function m(e,t){if(1&e){const e=l.EpF();l.TgZ(0,"ion-col"),l.TgZ(1,"div",9),l.NdJ("click",function(){const t=l.CHM(e).$implicit;return l.oxw().chooseBonusIndex(t)}),l._uU(2),l.qZA(),l.qZA()}if(2&e){const e=t.$implicit;l.xp6(2),l.Oqu(e)}}const b=function(){return["/waiting"]},d=[{path:"",component:(()=>{class e{constructor(e,t,s,n){this.route=e,this.router=t,this.gamingService=s,this.toastController=n,this.game={id:"",name:"",bonuses:[],participants:[],startTime:null,endTime:null},this.bonusNumbers=[]}ngOnInit(){this.refresh()}refresh(){const e=this.route.snapshot.paramMap.get("gamingId");console.log(e);const t=this.gamingService.getAccountJoinedGameId().subscribe(e=>{t.unsubscribe();const s=this.gamingService.getGame(e).subscribe(t=>{s.unsubscribe(),this.game.id=t.id,this.game.name=t.name,this.game.bonuses.splice(0,this.game.bonuses.length,...t.bonuses),this.game.participants.splice(0,this.game.participants.length,...t.participants),this.game.startTime=t.startTime,this.game.endTime=t.endTime;const n=this.gamingService.getGameAvailableBonuses(e).subscribe(e=>{n.unsubscribe(),this.init(e)},e=>{n.unsubscribe(),console.error(e)})},e=>{s.unsubscribe(),console.error(e)})},e=>{t.unsubscribe(),console.error(e)})}init(e){this.bonusNumbers.splice(0,this.bonusNumbers.length);for(let t of e)this.bonusNumbers.push(t+1)}chooseBonusIndex(e){return t=this,void 0,n=function*(){const t=yield this.toastController.create({message:"\u9009\u62e9\u6210\u529f\uff0c\u8bf7\u7b49\u5019\u5f00\u5956",duration:2e3}),s=yield this.toastController.create({message:"\u624b\u6162\u4e86\uff01\u8bf7\u9009\u62e9\u5176\u4ed6\u5361\u7247",duration:500}),n=yield this.toastController.create({message:"\u522b\u8d2a\u5fc3\uff01\u60a8\u5df2\u53c2\u52a0\u8fc7\u6e38\u620f\u4e86",duration:2e3}),i=this.gamingService.postChooseBonusIndex(this.game.id,+e-1).subscribe(e=>{i.unsubscribe(),t.present(),this.router.navigate(["/hall"])},e=>{console.error(e),i.unsubscribe(),"to late"==e?(s.present(),this.refresh()):"exist"==e&&(n.present(),this.router.navigate(["/hall"]))})},new((s=void 0)||(s=Promise))(function(e,i){function o(e){try{a(n.next(e))}catch(t){i(t)}}function r(e){try{a(n.throw(e))}catch(t){i(t)}}function a(t){var n;t.done?e(t.value):(n=t.value,n instanceof s?n:new s(function(e){e(n)})).then(o,r)}a((n=n.apply(t,[])).next())});var t,s,n}}return e.\u0275fac=function(t){return new(t||e)(l.Y36(r.gz),l.Y36(r.F0),l.Y36(p),l.Y36(o.yF))},e.\u0275cmp=l.Xpm({type:e,selectors:[["app-gaming"]],features:[l._Bn([p])],decls:17,vars:4,consts:[["mode","ios"],["slot","start"],[3,"routerLink"],[1,"ion-text-center"],["slot","end"],[3,"click"],[1,"padding-top-0"],["color","light",1,"ion-header-tip"],[4,"ngFor","ngForOf"],[1,"bonus-card",3,"click"]],template:function(e,t){1&e&&(l.TgZ(0,"ion-header"),l.TgZ(1,"ion-toolbar",0),l.TgZ(2,"ion-buttons",1),l.TgZ(3,"ion-button",2),l._uU(4," \u8fd4\u56de "),l.qZA(),l.qZA(),l.TgZ(5,"ion-title",3),l._uU(6),l.qZA(),l.TgZ(7,"ion-buttons",4),l.TgZ(8,"ion-button",5),l.NdJ("click",function(){return t.refresh()}),l._uU(9," \u5237\u65b0 "),l.qZA(),l.qZA(),l.qZA(),l.qZA(),l.TgZ(10,"ion-content"),l.TgZ(11,"ion-list",6),l.TgZ(12,"ion-list-header",7),l._uU(13,"\u8bf7\u9009\u62e9\u4efb\u610f\u5361\u7247"),l.qZA(),l.qZA(),l.TgZ(14,"ion-grid"),l.TgZ(15,"ion-row"),l.YNc(16,m,3,1,"ion-col",8),l.qZA(),l.qZA(),l.qZA()),2&e&&(l.xp6(3),l.Q6J("routerLink",l.DdM(3,b)),l.xp6(3),l.hij("",t.game.name," \u8fdb\u884c\u4e2d"),l.xp6(10),l.Q6J("ngForOf",t.bonusNumbers))},directives:[o.Gu,o.sr,o.Sm,o.YG,o.YI,r.rH,o.wd,o.W2,o.q_,o.yh,o.jY,o.Nd,n.sg,o.wI],styles:[".bonus-card[_ngcontent-%COMP%]{background-image:url(overed-game-prize-bgpng.296e09be424fd479d164.png);margin:0 auto;width:95px;height:95px;border:1px solid #b34850;text-align:center;line-height:95px;font-size:24px;font-weight:bolder;color:#e74b1c}"]}),e})()}];let f=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=l.oAB({type:e}),e.\u0275inj=l.cJS({imports:[[r.Bz.forChild(d)],r.Bz]}),e})();var Z=s(6538);let v=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=l.oAB({type:e}),e.\u0275inj=l.cJS({providers:[{provide:h.TP,useClass:Z.Q,multi:!0}],imports:[[n.ez,i.u5,h.JF,o.Pc,f]]}),e})()}}]);
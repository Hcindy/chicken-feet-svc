(self.webpackChunkchicken_feet_web=self.webpackChunkchicken_feet_web||[]).push([[5258],{5258:(e,s,t)=>{"use strict";t.r(s),t.d(s,{SetGamePageModule:()=>f});var o=t(8583),n=t(665),i=t(3083),r=t(8136),u=t(1406),a=t(205),c=t(5917),g=t(9773),l=t(639),h=t(1841);let m=(()=>{class e{constructor(e){this.http=e,this.ok=u.Z.ok}postSetChooser(e,s){return this.http.post(`games/${e}/bonuses/${s.bonusIndex-1}/chooser/${s.username}`,null).pipe((0,g.zg)(e=>e.result<this.ok?(0,a._)(e.message):(0,c.of)(e.result)))}postSetBeginAfterTime(e,s){return this.http.post(`games/${e}/startTime/${s}`,null).pipe((0,g.zg)(e=>e.result<this.ok?(0,a._)(e.message):(0,c.of)(e.result)))}}return e.\u0275fac=function(s){return new(s||e)(l.LFG(h.eN))},e.\u0275prov=l.Yz7({token:e,factory:e.\u0275fac}),e})();const Z=function(){return["/manage-game"]},p=[{path:"",component:(()=>{class e{constructor(e,s){this.route=e,this.setGameService=s,this.setChooser={bonusIndex:1,username:"",message:""}}ngOnInit(){this.gameId=this.route.snapshot.paramMap.get("gameId"),console.log(this.gameId)}submitChooser(){this.setChooser.message="";const e=this.setGameService.postSetChooser(this.gameId,this.setChooser).subscribe(s=>{e.unsubscribe(),this.setChooser.message="ok"},s=>{e.unsubscribe(),this.setChooser.message=s,console.error(s)})}submitBeginSoon(){const e=this.setGameService.postSetBeginAfterTime(this.gameId,60).subscribe(s=>{e.unsubscribe(),this.setBegin="ok"},s=>{e.unsubscribe(),this.setBegin=s,console.error(s)})}}return e.\u0275fac=function(s){return new(s||e)(l.Y36(r.gz),l.Y36(m))},e.\u0275cmp=l.Xpm({type:e,selectors:[["app-set-game"]],features:[l._Bn([m])],decls:34,vars:6,consts:[["mode","ios"],["slot","start"],[3,"routerLink"],[1,"ion-text-center"],[3,"click"],["placeholder","\u5927\u4e8e0\u7684\u6574\u6570","name","chooseBonusIndex",3,"ngModel","ngModelChange"],["chooseBonusIndex","ngModel"],["placeholder","\u7528\u6237\u540d","name","chooseUsername",3,"ngModel","ngModelChange"],["chooseUsername","ngModel"]],template:function(e,s){1&e&&(l.TgZ(0,"ion-header"),l.TgZ(1,"ion-toolbar",0),l.TgZ(2,"ion-buttons",1),l.TgZ(3,"ion-button",2),l._uU(4,"\u53d6\u6d88"),l.qZA(),l.qZA(),l.TgZ(5,"ion-title",3),l._uU(6,"\u8bbe\u7f6e\u6e38\u620f"),l.qZA(),l.qZA(),l.qZA(),l.TgZ(7,"ion-content"),l.TgZ(8,"ion-item"),l.TgZ(9,"ion-label"),l._uU(10,"\u9884\u8bbe\u83b7\u5956\u4eba"),l.qZA(),l.TgZ(11,"ion-button",4),l.NdJ("click",function(){return s.submitChooser()}),l._uU(12,"\u63d0\u4ea4"),l.qZA(),l.qZA(),l.TgZ(13,"ion-item"),l.TgZ(14,"ion-label"),l._uU(15,"\u5956\u9879\u7f16\u53f7"),l.qZA(),l.TgZ(16,"ion-input",5,6),l.NdJ("ngModelChange",function(e){return s.setChooser.bonusIndex=e}),l.qZA(),l.qZA(),l.TgZ(18,"ion-item"),l.TgZ(19,"ion-label"),l._uU(20,"\u83b7\u5956\u4eba"),l.qZA(),l.TgZ(21,"ion-input",7,8),l.NdJ("ngModelChange",function(e){return s.setChooser.username=e}),l.qZA(),l.qZA(),l.TgZ(23,"ion-item"),l.TgZ(24,"ion-label"),l._uU(25),l.qZA(),l.qZA(),l.TgZ(26,"ion-item"),l.TgZ(27,"ion-label"),l._uU(28,"\u9a6c\u4e0a\u5f00\u59cb"),l.qZA(),l.TgZ(29,"ion-button",4),l.NdJ("click",function(){return s.submitBeginSoon()}),l._uU(30,"\u63d0\u4ea4"),l.qZA(),l.qZA(),l.TgZ(31,"ion-item"),l.TgZ(32,"ion-label"),l._uU(33),l.qZA(),l.qZA(),l.qZA()),2&e&&(l.xp6(3),l.Q6J("routerLink",l.DdM(5,Z)),l.xp6(13),l.Q6J("ngModel",s.setChooser.bonusIndex),l.xp6(5),l.Q6J("ngModel",s.setChooser.username),l.xp6(4),l.Oqu(s.setChooser.message),l.xp6(8),l.Oqu(s.setBegin))},directives:[i.Gu,i.sr,i.Sm,i.YG,i.YI,r.rH,i.wd,i.W2,i.Ie,i.Q$,i.pK,i.j9,n.JJ,n.On],styles:[""]}),e})()}];let d=(()=>{class e{}return e.\u0275fac=function(s){return new(s||e)},e.\u0275mod=l.oAB({type:e}),e.\u0275inj=l.cJS({imports:[[r.Bz.forChild(p)],r.Bz]}),e})();var b=t(6538);let f=(()=>{class e{}return e.\u0275fac=function(s){return new(s||e)},e.\u0275mod=l.oAB({type:e}),e.\u0275inj=l.cJS({providers:[{provide:h.TP,useClass:b.Q,multi:!0}],imports:[[o.ez,n.u5,h.JF,i.Pc,d]]}),e})()}}]);
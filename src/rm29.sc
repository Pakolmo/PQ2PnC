;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
;(include sci.sh)
(include game.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm29 0
)

(local
	newAct
	newProp
	[local2 2]
	[local4 2]
	local6
	local7
	local8
	local9
	[local10 4]
	local14
	local15
	local16
	newAutoDoor
	local18
	local19
	
	
)
(procedure (localproc_005a)
	(Print &rest #at -1 15)
)

(procedure (localproc_0ef8)
	(return
		(switch currentCar
			(13
				(cond 
					((not (ego inRect: [local10 0] [local10 1] [local10 2] [local10 3]))
						(localproc_005a 29 44)
					)
					(workCarLocked
						(localproc_005a 29 45)
						(return 0)
					)
					(workCarTrunkOpened
						(localproc_005a 29 46)
						(return 0)
					)
					((ego has: iFieldKit)
						(localproc_005a 29 47)
						(return 0)
					)
					(else
						(carScript changeState: 4)
						(return 1)
					)
				)
			)
			(33
				(cond 
					(
						(not
							(ego inRect: [local10 0] [local10 1] [local10 2] [local10 3]))
						(localproc_005a 29 44)
					)
					(personalCarLocked
						(localproc_005a 29 45)
					)
					(else
						(carScript changeState: 4)
					)
				)
			)
		)
	)
)

(instance unTrunk of Prop
	(properties)
)

(instance rm29 of Room
	(properties
		picture 29
		style $0001
	)
	
	(method (init)
		(super init:)
		(NormalEgo)
		(if (!= isOnDuty 1) (= local16 1))
		(= local6
			(if (== prevRoomNum 33) else (== prevRoomNum 13))
		)
		(Load VIEW 0)
		(Load VIEW 20)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 265)
		(Load VIEW 75)
		(if local6 (HandsOff) else (HandsOn))
		(if (== currentCar 13)
			(= [local10 0] 32)
			(= [local10 1] 182)
			(= [local10 2] 44)
			(= [local10 3] 192)
			(= [local2 0] 27)
			(= [local2 1] 179)
			(= local14 42)
		else
			(= [local10 0] 44)
			(= [local10 1] 186)
			(= [local10 2] 61)
			(= [local10 3] 193)
			(= [local2 0] 43)
			(= [local2 1] 183)
			(= local14 55)
		)
		(= local8 100)
		(= local9 44)
		(= local15 30)
		((View new:) ;ARNIES
			view: 265
			loop: 1
			cel: 0
			posn: 187 40
			init:
			addToPic:
		)
		((View new:)
			view: 265
			loop: 1
			cel: (if local16 4 else 1) ;CLOSED or OPEN sign
			posn: 225 86
			init:
			addToPic:
		)
		((View new:) ;VISA credit card sign
			view: 265
			loop: 1
			cel: 2
			posn: 157 86
			init:
			addToPic:
		)
		((View new:) ;Mastercard credit card sign
			view: 265
			loop: 1
			cel: 3
			posn: 157 75
			init:
			addToPic:
		)
		(if (not local16)
			((View new:) ;RED car
				view: 54
				loop: 0
				cel: 2
				posn: 76 165
				init:
				addToPic:
			)
			((View new:) ;GREY car facing right
				view: 75
				loop: 2
				cel: 1
				posn: 299 165
				init:
				addToPic:
			)
			((View new:) ;YELLOW car facing right
				view: 75
				loop: 2
				cel: 2
				posn: 313 187
				init:
				addToPic:
			)
		)
		((View new:) ;baricade
			view: 265
			posn: 28 128
			loop: 2
			cel: 0
			init:
			addToPic:
		)
		((Prop new:) ;baricade lights
			view: 265
			loop: 3
			posn: 28 103
			init:
			setCycle: Forward
			cycleSpeed: 1
			startUpd:
		)
		((= newAutoDoor (AutoDoor new:))
			entranceTo: 30
			facingLoop: 3
			view: 265
			posn: 186 105
			setPri: 6
			init:
			stopUpd:
		)
		(= local7 (== roomCarParked curRoomNum))
		(if (!= prevRoomNum local15)
			(= local6 1)
			(HandsOff)
		else
			(= local6 0)
			(HandsOn)
		)
		((= newAct (Actor new:))
			view: 54
			setStep: 3 3
			setLoop: 0
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			posn: (if (not local7) local8 else local9) 185
			init:
			illegalBits: 0
		)
		(self setLocales: 153)
		(self setScript: rm29Script)
	)
	
	(method (dispose)
		(carScript dispose:)
		(stopScript dispose:)
		(DisposeScript 301)
		(super dispose:)
	)
)

(instance rm29Script of Script
	(properties)
	
	(method (doit)
		(if
			(or
				(< (ego x?) -5)
				(> (ego x?) 325)
				(> (ego y?) 210)
			)
			(localproc_005a 29 0)
			(if (not local16) (localproc_005a 29 1))
			(if (> (ego y?) 210)
				(ego setMotion: MoveTo (ego x?) 150)
			else
				(ego setMotion: MoveTo 150 (ego y?))
			)
		)
		(cond 
			(
			(and (ego inRect: 34 122 56 139) (not local19)) (localproc_005a 29 2) (= local19 1))
			((not (ego inRect: 34 122 56 139)) (= local19 0))
		)
		(if local16
			(newAutoDoor locked: 1)
		else
			(newAutoDoor locked: 0)
		)
		(cond 
			((== (newAutoDoor doorState?) 2) (curRoom newRoom: local15))
			(
				(and
					(== global132 local6)
					(== local6 1)
					(not (cast contains: newAct))
				)
				(= global132 0)
				(carScript changeState: 0)
			)
			(
			(and (== global132 (not local6)) (== (not local6) 1)) (= global132 0) (localproc_0ef8))
		)
		(curRoom setRegions: 950)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 0 posn: 0 0 init: setMotion: 0)
				(if (== currentCar 13)
					((= keith (Actor new:)) view: 20 init:)
				)
				(= [local4 0] 82)
				(= [local4 1] 190)
				(stopScript init:)
				(= workCarTrunkOpened 0)
				(newAct setMotion: MoveTo local9 185 stopScript)
				(if (!= roomCarParked curRoomNum)
					(= roomCarParked curRoomNum)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?)
			(return)
		)		
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '/trunk')
								(if (== currentCar 13)
									(if
										(and
											(ego inRect: 79 174 116 187)
											(cast contains: unTrunk)
										)
										(inventory
											carrying: {The car's trunk contains:}
											empty: {The car's trunk is empty.}
											showSelf: 13
										)
									else
										(localproc_005a 29 3)
									)
								else
									(localproc_005a 29 4)
								)
							)
							((Said '/building,cafe,arnie') (localproc_005a 29 5) (if local16 (localproc_005a 29 6)))
							((Said '[<around,at][/lot]') (localproc_005a 29 7) (if local16 (localproc_005a 29 6)))
							((Said '/chamber') (localproc_005a 29 8))
							((Said '/sign')
								(if (ego inRect: 160 105 215 128)
									(localproc_005a 29 9)
									(if local16
										(localproc_005a 29 10)
									else
										(localproc_005a 29 11)
									)
								else
									(localproc_005a 29 12)
								)
							)
							((Said '[<at,down][/ave]') (localproc_005a 29 13))
							((Said '[<at,up][/air]') (localproc_005a 29 14))
							((Said '/auto')
								(if local16
									(if (== currentCar 13)
										(localproc_005a 29 15)
									else
										(localproc_005a 29 16)
									)
								else
									(localproc_005a 29 17)
								)
							)
							((Said '/tree,bush') (localproc_005a 29 18))
							((Said '/door')
								(if local16
									(localproc_005a 29 19)
								else
									(localproc_005a 29 20)
								)
							)
							((Said '/construction') (localproc_005a 29 21))
							((Said '/pane') (localproc_005a 29 22))
						)
					)
					((Said 'drive')
						(if local6
							(localproc_005a 29 23)
						else
							(localproc_005a 29 24)
						)
					)
					((Said 'read/sign')
						(if (ego inRect: 160 105 215 128)
							(localproc_005a 29 9)
							(if local16
								(localproc_005a 29 10)
							else
								(localproc_005a 29 11)
							)
						else
							(localproc_005a 29 12)
						)
					)
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 79 174 116 187)
							(if workCarTrunkOpened
								(if (ego has: 10)
									(localproc_005a 29 25)
									(PutInRoom 10 13)
									(if (IsObject theFieldKit) (theFieldKit dispose:))
									(= fieldKitOpen 0)
								else
									(localproc_005a 29 26)
								)
							else
								(localproc_005a 29 27)
							)
						else
							(localproc_005a 29 28)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 79 174 116 187)
							(if workCarTrunkOpened
								(if (InRoom 10 13)
									(localproc_005a 29 29)
									(ego get: 10)
								else
									(localproc_005a 29 30)
								)
							else
								(localproc_005a 29 27)
							)
						else
							(localproc_005a 29 28)
						)
					)
					((Said 'enter/auto') (localproc_0ef8))
					((or (Said 'open/door') (Said 'get<in'))
						(cond 
							(local6 (= global132 1))
							((not (ego inRect: 160 105 215 128)) (localproc_0ef8))
							(local16 (localproc_005a 29 31))
							(else (localproc_005a 29 32))
						)
					)
					((Said 'exit,get<out/auto') (= global132 1))
					((Said 'unlock/door,auto')
						(if
							(ego
								inRect: [local10 0] [local10 1] [local10 2] [local10 3]
							)
							(cond 
								((and (== currentCar 13) (ego has: 3))
									(if workCarLocked
										(= workCarLocked 0)
										(localproc_005a 29 33)
									else
										(localproc_005a 29 34)
									)
								)
								((== currentCar 13) (localproc_005a 29 35))
							)
							(cond 
								((and (== currentCar 33) (ego has: 2))
									(if personalCarLocked
										(= personalCarLocked 0)
										(localproc_005a 29 33)
									else
										(localproc_005a 29 34)
									)
								)
								((== currentCar 33) (localproc_005a 29 35))
							)
						else
							(localproc_005a 29 36)
						)
					)
					((Said 'lock/door,auto')
						(if
							(ego
								inRect: [local10 0] [local10 1] [local10 2] [local10 3]
							)
							(if (== currentCar 13)
								(if (not workCarLocked)
									(= workCarLocked 1)
									(localproc_005a 29 37)
								else
									(localproc_005a 29 38)
								)
							)
							(if (== currentCar 33)
								(if (not personalCarLocked)
									(= personalCarLocked 1)
									(localproc_005a 29 37)
								else
									(localproc_005a 29 38)
								)
							)
						else
							(localproc_005a 29 36)
						)
					)
					((Said 'open,unlock/trunk')
						(if (== currentCar 13)
							(if (ego inRect: 79 174 116 187)
								(cond 
									(workCarTrunkOpened (Print 29 39))
									((ego has: 3) (carScript changeState: 7))
									(else (localproc_005a 29 40))
								)
							else
								(NotClose)
							)
						else
							(localproc_005a 29 4)
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar 13)
							(if (ego inRect: 79 174 116 187)
								(if workCarTrunkOpened
									(carScript changeState: 9)
									(= workCarTrunkOpened 0)
								else
									(Print 29 41)
								)
							else
								(NotClose)
							)
						else
							(localproc_005a 29 4)
						)
					)
				)
			)
		)			
		(if						
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))	
			)

			(if 	
					(ClickedInRect 173 201 61 106 event) ;look door arnie
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look

								(if local16
									(localproc_005a 29 19)
								else
									(localproc_005a 29 20)
								)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)




			(if 	
					(ClickedInRect 15 41 98 128 event) ;look construction
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
							(localproc_005a 29 21)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)

			(if 	
					(ClickedInRect 159 20 216 39 event) ;look sign
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
							(localproc_005a 29 12)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)

			
			(if 	(or
					(ClickedInRect 150 163 69 86 event) ;look arround
					(ClickedInRect 212 242 77 87 event) ;open closed
					)
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
							
							
												
					(if (ego inRect: 160 105 215 128)
						(localproc_005a 29 9) ;Visa. Mastercard.
						
						(if local16
							(localproc_005a 29 10) ;Cerrado.
							
						else
							(localproc_005a 29 11) ;abierto
							
						)
					else
						(localproc_005a 29 12) ;El restaurante se llama Arnie.
						
					)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
			
			(if

						(and
							(ClickedInRect 64 85 152 178 event) ;trunk
							(== currentCar 33) ;carPersonal
						)

					(event claimed: TRUE)
					(switch theCursor
						(998 ;look			
							(localproc_005a 29 4)

						)
						(995 ;use			
							(localproc_005a 29 4)

						)						
						(else
							(event claimed: FALSE)
						)
					)
				)

			(if

						(and
							(ClickedInRect 64 89 152 181 event) ;trunk
							(== currentCar 13) ;WorkCar
						)

					(event claimed: TRUE)
					(switch theCursor
						(998 ;look			
							(localproc_005a 29 4)

						)
						(110 ;maletin
							(if (ego inRect: 79 174 116 187)
							(if workCarTrunkOpened
								(if (ego has: 10)
									(localproc_005a 29 25)
									(PutInRoom 10 13)
									(if (IsObject theFieldKit) (theFieldKit dispose:))
									(= fieldKitOpen 0)
									(theGame setCursor: 995 (HaveMouse)) ;switch to empty hand
									(= itemIcon 900)
								else
									(localproc_005a 29 26)
								)
							else
								(localproc_005a 29 27)
							)
						else
							(localproc_005a 29 28)
						)
							
							
						)
						(995 ;use			

						(if (== currentCar 13)
							(if (ego inRect: 79 174 116 187)
								(cond 
									(workCarTrunkOpened 
										;(Print 29 39)
										(if (== currentCar 13)
							(if (ego inRect: 79 174 116 187)
								(if workCarTrunkOpened
									(carScript changeState: 9)
									(= workCarTrunkOpened 0)
								else
									(Print 29 41)
								)
							else
								(NotClose)
							)
						else
							(localproc_005a 29 4)
						)
										
										
										)
									((ego has: 3) 			
									(= workCarTrunkOpened 1)
									(unTrunk
										view: 51
										loop: 4
										cel: 0
										posn: [local4 0] [local4 1]
										setPri: 14
										init:
										setCycle: EndLoop self
									)
										
										(if (ego inRect: 79 174 116 187)
							(if workCarTrunkOpened
								(if (InRoom 10 13)
									(localproc_005a 29 29)
									(ego get: 10)
									(theGame setCursor: 110 (HaveMouse)) ;switch to empty hand
									(= itemIcon 110)
								else
;;;									(localproc_005a 29 30)
								)
							else
								(localproc_005a 29 27)
							)
						else
							(localproc_005a 29 28)
						)
										
										)
									(else (localproc_005a 29 40))
								)
							else
								(NotClose)
							)
						else
							(localproc_005a 29 4)
						)
						)						
						(else
							(event claimed: FALSE)
						)
					)
				)


				(if

						(and
							(ClickedInRect 20 45 157 180 event) ;doorcar
							(== currentCar 13) ;carWork
						)

					(event claimed: TRUE)
					(switch theCursor
						(995 ;use
							(cond 
							(local6 (= global132 1))
							((not (ego inRect: 160 105 215 128)) (localproc_0ef8))
							(local16 (localproc_005a 29 31))
							(else (localproc_005a 29 32))
							)
						)
						(998 ;look			
							(localproc_005a 29 4)

						)
						(else
							(event claimed: FALSE)
						)
					)
				)
		
		
		
			
			
;;;			
;;;			
;;;			
;;;			
;;;			(if ;clicked on personal car
;;;				(and
;;;;;;					(ClickedOnObj ourCar (event x?) (event y?))
;;;					(ClickedInRect 29 57 155 180 event) ;doorcar
;;;					(== currentCar carPersonal)
;;;				)
;;;				(event claimed: TRUE)
;;;				(switch theCursor
;;;					(998 ;look			
;;;						(localproc_005a 29 4)
;;;					)
;;;					(else
;;;						(event claimed: FALSE)
;;;					)
;;;				)
;;;			)
;;;			(if ;clicked on work car
;;;				(and
;;;					(ClickedOnObj ourCar (event x?) (event y?))
;;;					(== currentCar carWork)
;;;				)
;;;				(event claimed: TRUE)
;;;				(switch theCursor
;;;					(995 ;use
;;;						(if (ego inRect: 79 174 116 187)
;;;							(cond 
;;;								(workCarTrunkOpened 
;;;									;(Print 29 39) ;Ya est* abierta.
;;;									(if workCarTrunkOpened
;;;									(carScript changeState: 9)
;;;									(= workCarTrunkOpened 0)
;;;								else
;;;									(Print 29 41)
;;;								)
;;;									
;;;									
;;;									)
;;;								((ego has: 3) (carScript changeState: 7))
;;;								(else (localproc_005a 29 40))
;;;							)
;;;						else
;;;							(NotClose)
;;;						)	
;;;					)						
;;;					(998 ;look				
;;;						(if
;;;							(and
;;;								(ego inRect: 79 174 116 187)
;;;								(cast contains: unTrunk)
;;;							)
;;;							(inventory
;;;								carrying: {The car's trunk contains:}
;;;								empty: {The car's trunk is empty.}
;;;								showSelf: 13
;;;							)
;;;						else
;;;							(localproc_005a 29 3)
;;;						)	
;;;					)
;;;					(else
;;;						(event claimed: FALSE)	
;;;					)
;;;				)
;;;			)
		)
	)
)

(instance ourCar of Actor
	(properties)
)

(instance carScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: local14 186
					loop: 1
					cel: 0
					setCycle: Walk
					startUpd:
				)
				((= newProp (Prop new:))
					view: 51
					loop: (if (== currentCar 13) 2 else 0)
					cel: 0
					posn: [local2 0] [local2 1]
					setPri: 14
					init:
					setCycle: EndLoop self
				)
				(if (== currentCar carWork)
					(keith
						posn: 51 184
						setStep: 1 2
						setLoop: 1
						setCel: 0
						setPri: 13
						ignoreActors: 1
						illegalBits: 0
						startUpd:
						setMotion: MoveTo 55 174
					)
					(= workCarLocked 0)
				else
					(= personalCarLocked 0)
				)
			)
			(1
				(= local6 0)
				(newProp dispose:)
				(self cue:)
			)
			(2
				(HandsOn)
				(if (== currentCar 13)
					(if local16
						(localproc_005a 29 42 83)
					else
						(localproc_005a 29 43 83)
					)
					(keith setMotion: MoveTo 52 184 self)
				)
			)
			(3
				(keith posn: 340 340 ignoreActors: 0)
			)
			(4
				(HandsOff)
				(ego stopUpd:)
				(self cue:)
			)
			(5
				(ego setPri: 15)
				((= newProp (Prop new:))
					view: 51
					loop: (if (== currentCar 13) 2 else 0)
					cel: 0
					posn: [local2 0] [local2 1]
					setPri: 14
					init:
					setCycle: EndLoop self
				)
			)
			(6
				(curRoom newRoom: currentCar)
			)
			(7
				(= workCarTrunkOpened 1)
				(unTrunk
					view: 51
					loop: 4
					cel: 0
					posn: [local4 0] [local4 1]
					setPri: 14
					init:
					setCycle: EndLoop self
				)
			)
			(8 
;;;			(unTrunk stopUpd:)
			)
			(9
				(= workCarTrunkOpened 0)
				(unTrunk
					view: 51
					loop: 4
					cel: 2
					posn: [local4 0] [local4 1]
					setPri: 14
					startUpd:
					setCycle: CycleTo 0 -1 self
				)
			)
			(10 (unTrunk dispose:))
		)
	)
)

(instance stopScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego stopUpd:)
				(if (== currentCar 13) (keith stopUpd:))
			)
			(1
				(= global132 1)
				(newAct stopUpd: addToPic:)
			)
		)
	)
)
;;; Sierra Script 1.0 - (do not remove this comment)
(script# regJet)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)
(use Gun)

(public
	jet 0
	AirplanePrint 1
	InitPassengers 2
	seat1 3
)

(synonyms
	(attendant attendant)
)

(procedure (AirplanePrint)
	(Print &rest
		#at 10 10
		#font smallFont
	)
)

(procedure (InitPassengers)
	(addToPics
		add:
			toilet
			seat1
			seat2
			seat3
			seat4
			seat5
			seat6
			seat7
			seat8
			seat9
			seat10
			seat11
			seat12
			seat13
			seat14
			seat15
			seat16
			window1
			window2
			window3
			window4
			window5
			window6
			window7
			bath1
			bath2
			trash
			sink
			mirror
			LCutWall
	)
	(addToPics doit:)
)

(instance jet of Locale
	(method (handleEvent event &tmp evt)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(cond 
					((== (= evt (event message?)) `#6) ;load
						(event claimed: TRUE)
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							(
								(or
									(not (ego has: iAmmoClips))
									(and
										(== [numAmmoClips 1] [numAmmoClips 2])
										(== [numAmmoClips 2] 0)
									)
								)
								(Print 154 0)
							)
							([numAmmoClips bulletsInGun]
								(Print 154 1)
							)
							((and isHandsOff (not sittingInPlane)) 0)
							(else
								(Print 154 2 #time 4)
								(if (== bulletsInGun 1)
									(= bulletsInGun 2)
								else
									(= bulletsInGun 1)
								)
							)
						)
					)
					((== evt `#8)
						(event claimed: TRUE)
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							((== curRoomNum 41)
								(event claimed: FALSE)
							)
							(else
								(Print 154 3)
							)
						)
					)
				)
			)
			(mouseDown
				(if
					(and
						(== (event type?) evMOUSEBUTTON)
						(not (& (event modifiers?) emRIGHT_BUTTON))
					)
					(if
						(and
							(or
								(ClickedOnPicView seat2 (event x?) (event y?))
								(ClickedOnPicView seat3 (event x?) (event y?))
								(ClickedOnPicView seat4 (event x?) (event y?))
								(ClickedOnPicView seat5 (event x?) (event y?))
								(ClickedOnPicView seat6 (event x?) (event y?))
								(ClickedOnPicView seat7 (event x?) (event y?))
								(ClickedOnPicView seat8 (event x?) (event y?))
								(ClickedOnPicView seat9 (event x?) (event y?))
								(ClickedOnPicView seat10 (event x?) (event y?))
								(ClickedOnPicView seat11 (event x?) (event y?))
								(ClickedOnPicView seat12 (event x?) (event y?))
								(ClickedOnPicView seat13 (event x?) (event y?))
								(ClickedOnPicView seat14 (event x?) (event y?))
								(ClickedOnPicView seat15 (event x?) (event y?))
								(ClickedOnPicView seat16 (event x?) (event y?))
							)
							(== (event claimed?) FALSE)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998
								(AirplanePrint 154 37) ;There are all types of passengers on board:...
							)
							(996
								(if (not sittingInPlane)
									(if (== curRoomNum 42)
										(switch (Random 0 2)
											(0 (AirplanePrint 42 26))
											(1 (AirplanePrint 42 27))
											(else  (AirplanePrint 42 28))
										)
									else
										(switch (Random 81 85)
											(81 (AirplanePrint 154 11))
											(82 (AirplanePrint 154 12))
											(83 (AirplanePrint 154 13))
											(84 (AirplanePrint 154 14))
											(85 (AirplanePrint 154 15))
										)
									)
								else
									(Print 154 16)
								)	
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if (== (event claimed?) FALSE) ;override from pncMenu for room 40-43
						(event claimed: TRUE)
						(switch theCursor
							(999 ;walk
								(event claimed: FALSE)
							)
							(998 ;look
								(switch (Random 42 44)
									(42 (Print {It's just as it appears.}))
									(43 (Print {It doesn't look interesting.}))
									(44 (Print {You see nothing special.}))
;;;									(42 (Print {Es lo que parece.}))
;;;									(43 (Print {No parece interesante.}))
;;;									(44 (Print {No tiene nada de especial.}))
								)
							)
							(996 ;talk 
								(Print {(There is no response.)} #at -1 144) ;"(There is no response.)"
;;;								(Print {(No hay respuesta.)} #at -1 144) ;"(There is no response.)"								
							)
							(995 ;hand
								(Print {(What do you want to take?.)}) ;"What do you want to take?"
;;;								(Print {(^Qu+ quieres coger?)}) ;"What do you want to take?"								
							)
							(997 ;wait sierra
							)
							(990 ;clicked anywhere with gun
								(if gunDrawAllowed
									(Print 41 1) ;dont need to use gun right now
								else
									(Print 0 34)
								)
							)
							(100 ;or with gun inventory item 
								(if gunDrawAllowed
									(Print 41 1) ;dont need to use gun right now
								else
									(Print 0 34)
								)
							)
							(994 ;gun target
								(fire)
							)
							(else ;inventory item
								(Print {no need to use that here.}) ;"no need to use that here"
;;;								(Print {No hay ninguna necesidad de usar esto ah|.}) ;"no need to use that here"
							)
						)
					)
				)
			)
			(saidEvent
				(cond 
					((Said '/compartment')
						(AirplanePrint 154 4) ;The compartments above the seats are for storing carry-on luggage, but since you have none, you do not need to bother with them.
					)
					((Said '/door>')
						(cond 
							((Said 'open,beat')
								(cond 
									((& (ego onControl:) cLMAGENTA)
										(Print 154 5) ;The rest room is occupied.
									)
									((& (ego onControl:) cLRED)
										(Print 154 6) ;The cockpit door is locked. Access is for crew members only.
									)
									(else
										(NotClose)
									)
								)
							)
							((Said 'knock,beat')
								(cond 
									((& (ego onControl:) cLMAGENTA)
										(Print 154 7) ;It's empty. Just go in.
									)
									((& (ego onControl:) cLRED)
										(Print 154 8) ;You knock, but no one answers.
									)
									(else
										(NotClose)
									)
								)
							)
							((Said 'unlock')
								(Print 154 9)
							)
							(else
								(event claimed: TRUE)
								(Print 154 10)
							)
						)
					)
					((Said 'chat/passenger,dude,broad')
						(if (not sittingInPlane)
							(switch (Random 81 85)
								(81 (AirplanePrint 154 11))
								(82 (AirplanePrint 154 12))
								(83 (AirplanePrint 154 13))
								(84 (AirplanePrint 154 14))
								(85 (AirplanePrint 154 15))
							)
						else
							(Print 154 16)
						)
					)
					((Said 'pinch[/attendant]')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17) ;She's not here.
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 18) ;The stewardess screams out, "You low life! Leave your hands to yourself!"
							)
						)
					)
					((Said 'chat,call/attendant')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17) ;She's not here.
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 19) ;The stewardess does not seem interested in talking with you at this time.
							)
						)
					)
					((Said 'use,go/crapper,bathroom,(chamber<(bath,rest))')
						(Print 154 20) ;The bathroom is in the back of the plane.
					)
					((Said 'display/badge')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17) ;She's not here.
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 21) ;She looks at your ID and then says..."Hello there, Officer Bonds."
							)
						)
					)
					((Said '[kiss,fuck][/naked,boob,sex]')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17)
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 22) ;The stewardess leans over, and quietly whispers in your ear...
								(AirplanePrint 154 23) ;"You dirt bag!"
							)
						)
					)
					((Said 'fasten,deposit,wear,buckle/belt,belt')
						(cond 
							((not sittingInPlane)
								(Print 154 24) ;You are not sitting down.
							)
							(wearingSeatbelt
								(Print 154 25) ;Your seat belt is already buckled.
							)
							(else
								(Print 154 26) ;ok
								(= wearingSeatbelt TRUE)
							)
						)
					)
					(
					(Said 'unfasten,unbuckle,remove,(get<off)/belt,belt')
						(cond 
							((not sittingInPlane)
								(Print 154 24) ;;You are not sitting down.
							)
							((not wearingSeatbelt)
								(Print 154 27) ;Your seat belt is already unbuckled.
							)
							(wearingSeatbelt
								(Print 154 26) ;ok
								(= wearingSeatbelt FALSE)
							)
						)
					)
					((Said 'sat')
						(if sittingInPlane
							(Print 154 28) ;you already are
						else
							(Print 154 29) ;You are not near enough to your seat.
						)
					)
					((Said 'stand,(get<up)')
						(cond 
							(wearingSeatbelt
								(Print 154 30) ;It's a little hard to do that while your seat belt is fastened.
							)
							((not sittingInPlane)
								(Print 154 31)
							)
							(else
								(Print 154 32) ;You don't need to right now.
							)
						)
					)
					((Said 'buy,order')
						(Print 154 33) ;The stewardess is not serving drinks now.
					)
					((Said '/captain')
						(AirplanePrint 154 34))
					((Said 'meditate,nap')
						(Print 154 35)
					)
					((Said 'look>')
						(cond 
							((Said '/attendant')
								(if (not (cast contains: stewardess))
									(Print 154 17)
								else
									(AirplanePrint 154 36)
								)
							)
							((Said '/passenger')
								(AirplanePrint 154 37) ;There are all types of passengers on board: businessmen, families, singles, and old folks.
							)
							((Said '/dude,broad')
								(AirplanePrint 154 38)
							)
							((Said '/pane')
								(AirplanePrint 154 39)
							)
							((Said '/bench')
								(AirplanePrint 154 40)
							)
							((Said '/bathroom')
								(AirplanePrint 154 20)
							)
							((Said '[<at,around][/(noword,chamber,airplane)]')
								(if (ego inRect: 63 152 75 161)
									(AirplanePrint 154 41)
								else
									(AirplanePrint 154 42)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
			)
		)
	)
)

(instance seat1 of PicView
	(properties
		y 72
		x 205
		view 82
		loop 1
		cel 3
		priority 1
		signal ignrAct
	)
)

(instance seat2 of PicView
	(properties
		y 82
		x 186
		view 82
		loop 1
		cel 5
		priority 1
		signal ignrAct
	)
)

(instance seat3 of PicView
	(properties
		y 92
		x 165
		view 82
		loop 1
		priority 1
		signal ignrAct
	)
)

(instance seat4 of PicView
	(properties
		y 102
		x 146
		view 82
		loop 1
		cel 6
		priority 1
		signal ignrAct
	)
)

(instance seat5 of PicView
	(properties
		y 112
		x 125
		view 82
		loop 1
		cel 1
		priority 1
		signal ignrAct
	)
)

(instance seat6 of PicView
	(properties
		y 123
		x 103
		view 82
		loop 1
		priority 1
		signal ignrAct
	)
)

(instance seat7 of PicView
	(properties
		y 135
		x 80
		view 82
		loop 1
		cel 2
		priority 1
		signal ignrAct
	)
)

(instance seat8 of PicView
	(properties
		y 146
		x 58
		view 82
		loop 1
		cel 4
		priority 1
		signal ignrAct
	)
)

(instance seat9 of PicView
	(properties
		y 90
		x 253
		view 82
		loop 1
		cel 5
		priority 14
		signal ignrAct
	)
)

(instance seat10 of PicView
	(properties
		y 100
		x 234
		view 82
		loop 1
		cel 1
		priority 14
		signal ignrAct
	)
)

(instance seat11 of PicView
	(properties
		y 110
		x 213
		view 82
		loop 1
		cel 6
		priority 14
		signal ignrAct
	)
)

(instance seat12 of PicView
	(properties
		y 120
		x 193
		view 82
		loop 1
		cel 4
		priority 14
		signal ignrAct
	)
)

(instance seat13 of PicView
	(properties
		y 131
		x 171
		view 82
		loop 1
		priority 14
		signal ignrAct
	)
)

(instance seat14 of PicView
	(properties
		y 141
		x 151
		view 82
		loop 1
		cel 2
		priority 14
		signal ignrAct
	)
)

(instance seat15 of PicView
	(properties
		y 151
		x 131
		view 82
		loop 1
		cel 1
		priority 14
		signal ignrAct
	)
)

(instance seat16 of PicView
	(properties
		y 162
		x 109
		view 82
		loop 1
		cel 5
		priority 14
		signal ignrAct
	)
)

(instance window1 of PicView
	(properties
		y 49
		x 203
		view 82
		loop 6
		priority 0
	)
)

(instance window2 of PicView
	(properties
		y 59
		x 183
		view 82
		loop 6
		priority 0
	)
)

(instance window3 of PicView
	(properties
		y 69
		x 163
		view 82
		loop 6
		priority 0
	)
)

(instance window4 of PicView
	(properties
		y 80
		x 141
		view 82
		loop 6
		priority 0
	)
)

(instance window5 of PicView
	(properties
		y 91
		x 119
		view 82
		loop 6
		priority 0
	)
)

(instance window6 of PicView
	(properties
		y 103
		x 95
		view 82
		loop 6
		priority 0
	)
)

(instance window7 of PicView
	(properties
		y 116
		x 69
		view 82
		loop 6
		priority 0
	)
)

(instance bath1 of PicView
	(properties
		y 41
		x 246
		view 82
		signal ignrAct
	)
)

(instance bath2 of PicView
	(properties
		y 167
		x 65
		view 82
		loop 2
		cel 1
		priority 12
		signal ignrAct
	)
)

(instance toilet of PicView
	(properties
		y 178
		x 68
		view 82
		cel 4
		priority 14
		signal ignrAct
	)
)

(instance trash of PicView
	(properties
		y 181
		x 32
		view 82
		cel 3
		priority 14
		signal ignrAct
	)
)

(instance sink of PicView
	(properties
		y 159
		x 27
		view 82
		cel 2
		signal ignrAct
	)
)

(instance mirror of PicView
	(properties
		y 143
		x 21
		view 82
		cel 1
		signal ignrAct
	)
)

(instance LCutWall of PicView
	(properties
		y 54
		x 242
		view 82
		cel 5
		priority 2
		signal ignrAct
	)
)

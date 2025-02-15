;;; Sierra Script 1.0 - (do not remove this comment)
(script# 166)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)
(use PncMenu)

(local
	helpMessage
)

(public
	rm166 0
)
(instance locWestRose of Prop   ;west Rose
	(properties
		x 72
		y 119
	)
)

(instance locHome of Prop   ;west Peach
	(properties
		x 17
		y 121
	)
)


(instance locInn of Prop   ;753 Third Street
	(properties
		x 101
		y 42
	)
)

(instance locAirport of Prop
	(properties
		x 3
		y 53
	)
)

(instance locJail of Prop
	(properties
		x 226
		y 175
	)
)

(instance locCove of Prop
	(properties
		x 288
		y 175
	)
)

(instance locArnie of Prop
	(properties
		x 99
		y 92
	)
)
(instance locMall of Prop
	(properties
		x 71
		y 41
	)
)
(instance locOffice of Prop
	(properties
		x 200
		y 120
	)
)


(local
 	tempCur = 995
)


(instance rm166 of Room
	(properties
		picture  166
		style IRISIN
	)
	
	(method (init)
		;(curRoom setRegions: 950)	
		(Load VIEW 166)
		(super init:)
		(= gunFireState gunUSELESS)
		(User canInput: FALSE canControl: TRUE)
		(locCove
			view: 166
			loop: 0
			cel: 0
			init:
		)
		(locArnie
			view: 166
			loop: 0
			cel: 0
			init:
		)

		(locMall
			view: 166
			loop: 0
			cel: 0
			init:
		)
		(locOffice
			view: 166
			loop: 0
			cel: 0
			init:
		)	
		(locJail
			view: 166
			loop: 0
			cel: 0
			init:
		)	
		(locAirport
			view: 166
			loop: 0
			cel: 0
			init:
		)
		(locInn
			view: 166
			loop: 0
			cel: 0
			init:
		)
		(locHome
			view: 166
			loop: 0
			cel: 0
			init:
		)
		(if (Btst fPnCHideWestRose)
			(locWestRose
				view: 166
				loop: 0
				cel: 0
				init:
			)
		)	
	)
	
	(method (doit)
		(if (== theCursor 103)
			(= tempCur 103)
			(theGame setCursor: 995 (HaveMouse))
		)
	)
	
	(method (dispose)
		(theGame setCursor: tempCur (HaveMouse))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?)
			(return)
		)
		(if (== (event type?) evMOUSEBUTTON)
			(if (& (event modifiers?) emRIGHT_BUTTON)
				(event claimed: TRUE)
				(if (== theCursor 995)
					(theGame setCursor: 998 (HaveMouse))
				else
					(theGame setCursor: 995 (HaveMouse))
				)	
			else
				(if (ClickedOnObj locWestRose (event x?) (event y?)) ;West Rose
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
							(Print {West Rose})
						)
						(995 ;hand
							(= getBody 0)
							(= driveDest 27)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)			
				(if (ClickedOnObj locHome (event x?) (event y?)) ;Home
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
;;;							(Print {Marie's Home})
							(Print {Casa de Marie})
						)
						(995 ;hand
							(= driveDest 31)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)			
				(if (ClickedOnObj locInn (event x?) (event y?)) ;Inn
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
;;;							(Print {The inn})
							(Print {La posada})
						)
						(995 ;hand
							(= driveDest 25)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if (ClickedOnObj locAirport (event x?) (event y?)) ;airport
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
;;;							(Print {The airport})
							(Print {El aeropuerto})
						)
						(995 ;hand
							(= driveDest 14)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if (ClickedOnObj locJail (event x?) (event y?)) ;jail
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
;;;							(Print {The jail})
							(Print {La c*rcel})
						)
						(995 ;hand
							(= driveDest 22)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if (ClickedOnObj locOffice (event x?) (event y?)) ;Office
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
;;;							(Print {the Office})
							(Print {la Oficina})
						)
						(995 ;hand
							(= driveDest 1)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if (ClickedOnObj locMall (event x?) (event y?)) ;cove
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
							(Print {Oak Tree Mall})
						)
						(995 ;hand
							(= driveDest 67)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if (ClickedOnObj locCove (event x?) (event y?)) ;cove
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
							(Print {Cotton Cove})
						)
						(995 ;hand
							(= driveDest 61)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if (ClickedOnObj locArnie (event x?) (event y?)) ;cove
					(event claimed: TRUE)
					(switch theCursor
						(998 ;look
;;;							(Print {Arnie's Resturant})
							(Print {Restaurante Arnie})
						)
						(995 ;hand
							(= driveDest 29)
							(theGame newRoom: prevRoomNum)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if
					(and
						(ClickedInRect 0 320 0 190 event)
						(== (event claimed?) FALSE)
					)
					(event claimed: TRUE) ;ignore all other clicks
					(++ helpMessage)
					(if (> helpMessage 3)
;;;						(Print {Use the hand or eye icon on one of the yellow location markers.})
						(Print {Usa el icono de la mano o del ojo en una de las marcas amarillas de direcci/n.})
						(= helpMessage 0)
					)
				)
			)
		)
	)
)
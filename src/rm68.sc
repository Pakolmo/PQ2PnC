;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)


(public
	rm68 0
)
(synonyms
	(box compartment)
)

(local
	siren
	gloveBox
	gloveBoxDoor
	registration
	holster
	bullets
	fingerprintState
	dustedGloveBox
	gloveBoxIsOpen
	dontHaveFieldKit
;;;	iFieldKit
;;;	fKit
;;;	teJustCenter
	bullets1
	newEvent
)
(instance rm68 of Room
	(properties
		picture 93
		style $0003
	)
	
	(method (init)
		(= gunFireState 3)
		(= gunNotNeeded 1)
		(HandsOff)
		(User canInput: FALSE canControl: 1)
		(self setLocales: 153) ;FIELD KIT
		(Load rsVIEW 257)
		(Load rsVIEW 75)
		(= fingerprintState 0)
		(= dustedGloveBox 0)
		(= gloveBoxIsOpen 0)
		(super init:)
		(self setScript: rm68Script)
		((inventory at: 34) moveTo: curRoomNum)
		
	)
	
	(method (dispose)

	
		(HandsOn)
		(super dispose:)
	)
)

(instance rm68Script of Script
	(properties)
	
	(method (doit)

		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= siren (Prop new:))
					view: 75
					loop: 7
					cel: 0
					posn: 148 38
					setPri: 0
					setCycle: Forward
					init:
				)
			)
			(1
				((= gloveBox (View new:))
					view: 257
					loop: 0
					cel: 0
					posn: 270 145
					setPri: 10
					init:
					ignoreActors:
					stopUpd:
				)
				((= gloveBoxDoor (Prop new:))
					view: 257
					setLoop: 1
					setCel: 0
					posn: 266 137
					setPri: 13
					init:
					stopUpd:
					ignoreActors:
				)
				((= registration (View new:))
					view: 257
					loop: 0
					cel: 2
					posn: 266 128
					setPri: 11
					init:
					ignoreActors:
					stopUpd:
				)
				(if (== (ego has: 21) 0)
					((= holster (View new:))
						view: 257
						loop: 0
						cel: 1
						posn: 257 128
						setPri: 11
						init:
						stopUpd:
						ignoreActors:
					)

				)
				(if (== (ego has: 20) 0)
					((= bullets (View new:))
						view: 257
						loop: 0
						cel: 3
						posn: 259 128
						setPri: 12
						init:
						stopUpd:
						ignoreActors:
					)

				)
				(self cue:)
			)
			(2
				(gloveBoxDoor startUpd: setCycle: EndLoop)
				(= gloveBoxIsOpen 1)
			)
			(3
				(gloveBoxDoor setCycle: BegLoop self)
				(= gloveBoxIsOpen 0)
			)
			(4
				(gloveBox dispose:)
				(gloveBoxDoor dispose:)
				(registration dispose:)
				(if (== (ego has: 20) 0) (bullets dispose:))
				(if (== (ego has: 21) 0) (holster dispose:))
			)
			(5 (curRoom newRoom: 67))
		)
	)
	
	(method (handleEvent event)
		
		(if (event claimed?) (return))
		
;;;		(if (!= (event type?) evSAID) (return))
	
	
			(cond
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emRIGHT_BUTTON))
				)

				(if
					(and
						(ClickedInRect 224  300 78 131 event) ;guantera
						(cast contains: gloveBox)
					)
						

			(event claimed: TRUE)
					(switch theCursor
						(208 ;fingerprint tape
							(cond 
								((not (ego has: 10)) (Print 68 11))
								((not fingerprintState) (Print 68 16))
								((== fingerprintState 1) (Print 68 17))
								((ego has: 22) (Print 68 18))
								(else
									(global123 setPri: 0)
									(Print 68 19 #draw)
									(SolvePuzzle 3)
									(global123 setPri: 15)
									(ego get: 22)
									(= fingerprintState 0)
								)
							)
									)
									
									
						
						
						(206 ;Use

							(cond 
								((not (ego has: 10)) (Print 68 11))
								(dustedGloveBox
									(Print 68 12)
									(= fingerprintState 2)
									(= dustedGloveBox 1)
								)
								(else
									(global122 setPri: 0)
									(global120 setPri: 0)
									(if (Btst 41)
										(Print 68 13 #draw)
										(= fingerprintState 1)
									else
										(Print 68 14 #draw)
										(= dustedGloveBox 1)
										(= fingerprintState 2)
									)
									(global122 setPri: 15)
									(global120 setPri: 14)
								)
							)
						)
						(else
							(event claimed: FALSE)
						)
					)
		)
			







				(if
					(and
						(ClickedInRect 252 267 126 132 event) ;bullets
												(cast contains: gloveBox)
					)
					(event claimed: TRUE)
					(switch theCursor
						(995 ;Use
							(Print 68 20)
							
						)
						(201
							(cond 
;;;								((ego has: 20) ((inventory at: 20) showSelf:))
								((not gloveBoxIsOpen) (Print 68 3))
								((InRoom 20)
;;;									(bullets1 dispose:)
									(bullets dispose:)
									(Print 68 4 #draw)
									(SolvePuzzle 1)
									(ego get: 20)
								)
								(else (Print 68 5))
							)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				

				(if
					(and
						(ClickedInRect 274 288 116 131 event) ;regitration
												(cast contains: gloveBox)
					)
					(event claimed: TRUE)
					(switch theCursor
						(995 ;Use
							(Print 68 1)
						)
						(998 ;Look
							(Print 68 1)
						)
						(201
							(Print 68 1)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)


				(if
					(and
						(ClickedInRect 243 272 118 126 event) ;holster
												(cast contains: gloveBox)
					)
					(event claimed: TRUE)
					(switch theCursor
						(201
							(cond 
;;;								((ego has: 21) ((inventory at: 21) showSelf:))
								((not gloveBoxIsOpen) (Print 68 2))
								((InRoom 21)
									(holster dispose:)
														((= bullets1 (View new:))
						view: 257
						loop: 0
						cel: 3
						posn: 259 128
						setPri: 12
						init:
						stopUpd:
						ignoreActors:
					)
					(bullets1 dispose:)
									(Print 68 6 #draw)
									(SolvePuzzle 1)
									(ego get: 21)
									(Bset 50)
								)
								(else (Print 68 7))
							)
;;;							(cond  ;the bullets
;;;								((ego has: 20) ((inventory at: 20) showSelf:))
;;;								((not gloveBoxIsOpen) (Print 68 3))
;;;								((InRoom 20)
;;;									(bullets dispose:)
;;;									(Print 68 4 #draw)
;;;									(SolvePuzzle 1)
;;;									(ego get: 20)
;;;								)
;;;								(else (Print 68 5))
;;;							)
						)
						(995 ;Use 
							(Print 68 21)
							

						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				
				(if
					(and
						(ClickedInRect 303 314 88 99 event) ;guantera abierta cerrar
						(cast contains: gloveBox)
					)
					(event claimed: TRUE)
					(switch theCursor
						(995 ;Use close
							(if (== gloveBoxIsOpen 1)
								(= gloveBoxIsOpen 0)
								(rm68Script changeState: 3)
							else
								(Print 68 0)
							)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
			
			
				(if (== theCursor 110) ;use fieldkit
								(= newEvent (Event new:))
								(newEvent
								    type: evKEYBOARD
								    message: {open briefcase}
								    modifiers: 999
								    claimed: 0
								)
								(User handleEvent: newEvent)
								(newEvent dispose:)
				)
			
			
			
				(if (== theCursor 999) ;use walk to close.


					(cond
						((if (ego has: 20)

					 )(rm68Script changeState: 5))

					 
					
						((if (ego has: 21)

						)(rm68Script changeState: 5))
						((if (not (ego has: 10))

						)(rm68Script changeState: 5))
						
					)
					
	
							
				)
			
				
				









				(if

						(ClickedInRect 141  281 18 72 event) ;thecarwindows
					(event claimed: TRUE)
					(switch theCursor
						(206 ;Use fingerprint powder
							(if (not (ego has: 10))
								(Print 68 11)
							else
								(global122 setPri: 0)
								(global120 setPri: 0)
								(Print 68 15 #draw)
								(global122 setPri: 15)
								(global120 setPri: 14)
								(= fingerprintState 1)
							)
						)
						(208 ;tape
							(cond 
								((not (ego has: 10)) (Print 68 11))
								((not fingerprintState) (Print 68 16))
								((== fingerprintState 1) (Print 68 17))
								((ego has: 22) (Print 68 18))
								(else
									(global123 setPri: 0)
									(Print 68 19 #draw)
									(SolvePuzzle 3)
									(global123 setPri: 15)
									(ego get: 22)
									(= fingerprintState 0)
								)
							)
									
										
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
			


				(if
					(and
						(ClickedInRect 224  300 78 131 event) ;guantera
						(not (cast contains: gloveBox))
					)
						

					(event claimed: TRUE)
					(switch theCursor
												(208 ;tape
							(cond 
								((not (ego has: 10)) (Print 68 11))
								((not fingerprintState) (Print 68 16))
								((== fingerprintState 1) (Print 68 17))
								((ego has: 22) (Print 68 18))
								(else
									(global123 setPri: 0)
									(Print 68 19 #draw)
									(SolvePuzzle 3)
									(global123 setPri: 15)
									(ego get: 22)
									(= fingerprintState 0)
								)
							)
									
										
						)
						
						
						
						(206
							(cond 
					((not (ego has: 10)) (Print 68 11))
					(dustedGloveBox
						(Print 68 12)
						(= fingerprintState 2)
						(= dustedGloveBox 1)
					)
					(else
						(global122 setPri: 0)
						(global120 setPri: 0)
						(if (Btst 41)
							(Print 68 13 #draw)
							(= fingerprintState 1)
						else
							(Print 68 14 #draw)
							(= dustedGloveBox 1)
							(= fingerprintState 2)
						)
						(global122 setPri: 15)
						(global120 setPri: 14)
					)
				)
						)
						(998 ;look
							(if (== gloveBoxIsOpen 1)
;;;								(inventory
;;;									carrying: {In the glove compartment, you see:}
;;;									empty:
;;;										{Other than the car's registration, the glove compartment is empty.}
;;;									showSelf: 68
;;;								)
							else
								(Print 68 0)
							)	 	
						)
						(995 ;Open box
							(if (== gloveBoxIsOpen 0)
								(= gloveBoxIsOpen 1)
								(Print 68 9)
								(Bset 41)
								(rm68Script changeState: 1)
							else
								(Print 68 10)
							)
						)
						(else
							(event claimed: FALSE)
				
						)
					)
				)
				
				
				
				
			)
			)	
	
		
		(cond 
			((Said 'look/box')
				(if (== gloveBoxIsOpen 1)
					(inventory
						carrying: {In the glove compartment, you see:}
						empty:
							{Other than the car's registration, the glove compartment is empty.}
						showSelf: 68
					)
				else
					(Print 68 0)
				)
			)
			(
				(Said
					'get,read,look/registration,newspaper,card[<registration]'
				)
				(if (== gloveBoxIsOpen 1)
					(registration setPri: 1)
					(Print 68 1 #draw)
					(registration setPri: 11)
				else
					(Print 68 2)
				)
			)
			((Said 'get,look/ammo,ammo,bullet')
				(cond 
					((ego has: 20) ((inventory at: 20) showSelf:))
					((not gloveBoxIsOpen) (Print 68 3))
					((InRoom 20)
						(bullets dispose:)
						(Print 68 4 #draw)
						(SolvePuzzle 1)
						(ego get: 20)
					)
					(else (Print 68 5))
				)
			)
			((Said 'get,look/gunbelt')
				(cond 
					((ego has: 21) ((inventory at: 21) showSelf:))
					((not gloveBoxIsOpen) (Print 68 2))
					((InRoom 21)
						(holster dispose:)
						(Print 68 6 #draw)
						(SolvePuzzle 1)
						(ego get: 21)
						(Bset 50)
					)
					(else (Print 68 7))
				)
			)
			(
				(and
					(Said 'frisk,look>')
					(or
						(Said '<below/bench')
						(Said '<back/bench')
						(Said '<below/dash')
						(Said '/ashtray,auto,console,visor,dash,floor')
					)
				)
				(Print 68 8)
			)
			((Said 'open/box,box')
				(if (== gloveBoxIsOpen 0)
					(= gloveBoxIsOpen 1)
					(Print 68 9)
					(Bset 41)
					(rm68Script changeState: 1)
				else
					(Print 68 10)
				)
			)
			((Said 'close/box,box')
				(if (== gloveBoxIsOpen 1)
					(= gloveBoxIsOpen 0)
					(rm68Script changeState: 3)
				else
					(Print 68 0)
				)
			)
			(
			(or (Said 'exit,exit[/auto]') (Said 'close/door')) (rm68Script changeState: 5))
			(
				(or
					(Said 'deposit,use,apply/powder/box,box')
					(Said 'dust,powder/box,box')
				)
				(cond 
					((not (ego has: 10)) (Print 68 11))
					(dustedGloveBox
						(Print 68 12)
						(= fingerprintState 2)
						(= dustedGloveBox 1)
					)
					(else
						(global122 setPri: 0)
						(global120 setPri: 0)
						(if (Btst 41)
							(Print 68 13 #draw)
							(= fingerprintState 1)
						else
							(Print 68 14 #draw)
							(= dustedGloveBox 1)
							(= fingerprintState 2)
						)
						(global122 setPri: 15)
						(global120 setPri: 14)
					)
				)
			)
			(
				(or
					(Said 'dust,powder/pane,door,wheel,bench,mirror,handle')
					(Said
						'apply,use,deposit/powder/pane,door,wheel,bench,mirror,handle'
					)
				)
				(if (not (ego has: 10))
					(Print 68 11)
				else
					(global122 setPri: 0)
					(global120 setPri: 0)
					(Print 68 15 #draw)
					(global122 setPri: 15)
					(global120 setPri: 14)
					(= fingerprintState 1)
				)
			)
			((Said 'deposit,apply,use,hoist/print,tape')
				(cond 
					((not (ego has: 10)) (Print 68 11))
					((not fingerprintState) (Print 68 16))
					((== fingerprintState 1) (Print 68 17))
					((ego has: 22) (Print 68 18))
					(else
						(global123 setPri: 0)
						(Print 68 19 #draw)
						(SolvePuzzle 3)
						(global123 setPri: 15)
						(ego get: 22)
						(= fingerprintState 0)
					)
				)
			)
			((Said '(deposit,place)/*/box,box')
				(cond 
					((and (Said '/bullet,ammo/*') (ego has: 20)) (Print 68 20))
					((and (Said '/gunbelt/*') (ego has: 21)) (Print 68 21))
					(else (event claimed: 1) (Print 68 22))
				)
			)
			
		)				
		
			
			
			
			
		)
	)


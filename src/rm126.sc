;;; Sierra Script 1.0 - (do not remove this comment)
(script# 126)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm126 0
)

(local
	pipe
;;;	cabinet
	local2
	[sewage 4]
	local7
	cabinetIsOpen
	[str 100]
)
(instance cabinet of Prop
)
(instance gasMask of View
)
(instance rm126 of Room
	(properties
		picture 70
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(ego
			;view: (if (not gunDrawn) 0 else 6)
			view:
				(cond 
					(wearingGasMask (if gunDrawn 306 else 296))
					(gunDrawn 6)
					(else 0)
				)
			x: (if (== prevRoomNum 125) 12 else 310)
			y: (if (<= (ego y?) 115) 100 else 140)
			init:
		)
		(HandsOn)
		((= pipe (Prop new:))
			view: 92
			loop: 0
			cel: 0
			posn: 305 115
			setPri: 1
			ignoreActors: 1
			init:
			stopUpd:
		)
		((Prop new:)
			view: 92
			loop: 2
			cel: 0
			posn: 114 116
			setPri: 1
			setCycle: Forward
			ignoreActors: 1
			init:
		)
;;;		((View new:)
		(gasMask
			view: 92
			loop: 4
			cel: (if (ego has: 32) 0 else 1)
			ignoreActors:
			posn: 166 85
			setPri: 1
			init:
			addToPic:
		)
;;;		((= cabinet (Prop new:))
		(cabinet
			view: 92
			loop: 3
			cel: 0
			ignoreActors:
			posn: 166 85
			setPri: 2
			init:
			;stopUpd:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 237 130
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 120 126
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 133 173
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 3] (Prop new:))
			view: 99
			loop: 0
			cel: 0
			posn: 192 125
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([sewage 0] stopUpd:)
			([sewage 1] stopUpd:)
		)
		(if (< howFast 30)
			([sewage 2] stopUpd:)
			([sewage 3] stopUpd:)
		)
		(= local7 0)
		(sewerRat
			name: 7
			posn: 287 57
			setLoop: 2
			ignoreActors: 1
			init:
			setMotion: MoveTo -100 57 sewerRat
		)
		(sewerLight posn: 167 54 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego x?) 5) (curRoom newRoom: 125))
			((>= (ego x?) 315) (curRoom newRoom: 127))
			((not (ego inRect: 292 92 303 108)) (= local7 0))
			((not local7)
				(= local7 1)
				(if (== (Random 0 4) 3)
					(= sewerCutscene 1)
					(ego setScript: downTheDrain)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'get/mask')
						(cond 
							((ego has: 32) (Print 126 0))
							((not cabinetIsOpen) (Print 126 1))
							(else
								(Print 126 2)
								(ego get: 32)
								((View new:)
									view: 92
									loop: 4
									cel: 0
									ignoreActors:
									posn: 166 85
									setPri: 1
									init:
									addToPic:
								)
								(SolvePuzzle 4)
							)
						)
					)
					((Said 'open/cabinet,compartment,door')
						(cond 
							(cabinetIsOpen (Print 126 3))
							((ego inRect: 145 90 196 105) (cabinetScript changeState: 1))
							(else (NotClose))
						)
					)
					((Said 'look/mask')
						(cond 
							((ego has: 32) (event claimed: 0))
							((not cabinetIsOpen) (Print 126 1))
							(else (Print 126 4))
						)
					)
					((Said 'look/cabinet,compartment')
						(cond 
							((not cabinetIsOpen) (Print 126 5))
							((InRoom 32) (Print 126 6))
							(else (Print 126 7))
						)
					)
					((Said 'look[<at,around][/(!*,chamber)]')
						(Print 126 8)
						(Print
							(Format
								@str
								126
								9
								(cond 
									((not cabinetIsOpen) { closed})
									((InRoom 32) {open with a gas mask in it})
									(else { open and empty})
								)
							)
						)
					)
					((Said 'look/wall') (Print 126 10))
					((Said 'close/cabinet,compartment,door')
						(cond 
							((not cabinetIsOpen) (Print 126 3))
							((ego inRect: 145 94 196 105) (cabinetScript changeState: 3))
							(else (NotClose))
						)
					)
					((Said '(deposit<back),replace/mask') (if (ego has: 32) (Print 126 11) else (DontHave)))
				)
			)
		)
		(cond
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emRIGHT_BUTTON))
				)
				
				
	

					(if	(ClickedInRect 1 5 91 104 event) ;left up
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo -4 97 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
					(if	(ClickedInRect 1 3 130 138 event) ;left down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo -5 133 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
				
					(if	(ClickedInRect 310 319 91 103 event) ;up down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 323 96 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
						
					(if	(ClickedInRect 314 319 131 139 event) ;down right
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 326 136 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
						
		
			
				
				
				
				
				
				
				
				
				
				
				
				(if
					(and
						(ClickedOnObj gasMask (event x?) (event y?)) ;gasmask
						(== (event claimed?) FALSE)
					)
					(if
						(and
							cabinetIsOpen
							(InRoom 32)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998
								(cond 
									((ego has: 32) (event claimed: 0))
									((not cabinetIsOpen) (Print 126 1))
									(else (Print 126 4))
								)
							)
							(995
								(cond 
									((ego has: 32) (Print 126 0))
									((not cabinetIsOpen) (Print 126 1))
									(else
										(Print 126 2)
										(ego get: 32)
										((View new:)
											view: 92
											loop: 4
											cel: 0
											ignoreActors:
											posn: 166 85
											setPri: 1
											init:
											addToPic:
										)
										(SolvePuzzle 4)
									)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)										
				(if
					(and
						(ClickedOnObj cabinet (event x?) (event y?)) ;clicked on cabinet
						(== (event claimed?) FALSE)
					)
					(event claimed: TRUE)
					(switch theCursor
						(998
							(cond 
								((not cabinetIsOpen)
									(Print 126 5) ;The cabinet is closed.
								)
								((InRoom 32) ;gas mask
									(Print 126 6) ;You see a gas mask in the cabinet.
								)
								(else
									(Print 126 7) ;The cabinet is empty.
								)
							)
						)
						(995
							(if (ego inRect: 145 90 196 105)
								(cond
									(cabinetIsOpen
										(cabinetScript changeState: 3)
									)
									(else
										(cabinetScript changeState: 1)
									)
								)
							else
								(NotClose)
							)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if
					(and
						(ClickedOnObj ego (event x?) (event y?)) ;clicked on ego mask
						(== (event claimed?) FALSE)
					)
					(event claimed: TRUE)
					(switch theCursor
						(130
							(if (ego has: 30)
								(Print 205 26)
							else
								(DontHave)
							)				
						)
						(995
							(cond 
								((not (ego has: 32))
									(Print 205 28)
								)
								((not wearingGasMask)
									(Print 205 29)
								)
								(else
									(= wearingGasMask FALSE)
									(if (== (ego view?) 296)
										(ego view: 0)
									else
										(ego view: 6)
									)
								)
							)
						)
						(132
							(cond 
								((not (ego has: 32))
									(Print 205 30)
								)
								((== methaneGasTimer -1)
									(Print 205 31)
								)
								(wearingGasMask
									(Print 205 32)
								)
								(else
									(= wearingGasMask TRUE)
									(if (== (ego view?) 0)
										(ego view: 296)
									else
										(ego view: 306)
									)
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
						(ClickedInRect 1 319 21 54 event) ;up
						(== (event claimed?) FALSE)
					)
					(event claimed: TRUE)
					(switch theCursor
						(998
							(Print 126 8)
							(Print
								(Format
									@str
									126
									9
									(cond 
										((not cabinetIsOpen) { cerrado})
										((InRoom 32) {abierto con una m*scara dentro})
										(else { abierto pero vac|o})
									)
								)
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

(instance cabinetScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(cabinet setCycle: EndLoop self)
				(= cabinetIsOpen 1)
			)
			(2 
				;(cabinet stopUpd:)
			)
			(3
				(cabinet setCycle: BegLoop self)
				(= cabinetIsOpen 0)
			)
			(4
				;(cabinet stopUpd:)
			)
		)
	)
)

(instance downTheDrain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= sewerCutscene 1)
				(ego
					view: 92
					setLoop: 1
					cel: 0
					yStep: 1
					illegalBits: 0
					setMotion: MoveTo 304 110
					setCycle: CycleTo 2 1
				)
				(pipe setCycle: CycleTo 3 1 self)
				(cSound stop: number: 24 loop: 1 priority: 12 play:)
			)
			(1
				(ego yStep: 3 setMotion: MoveTo 304 115 setCycle: CycleTo 4 1)
				(pipe setCycle: CycleTo 6 1 self)
			)
			(2
				(ego setMotion: MoveTo 304 125 setCycle: CycleTo 7 1)
				(pipe setCycle: CycleTo 10 1)
				(= cycles 10)
			)
			(3
				(Print 126 12)
				(Print 126 13)
				(Print 126 14)
				(ego dispose:)
				(EgoDead 126 15)
			)
		)
	)
)

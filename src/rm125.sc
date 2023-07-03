;;; Sierra Script 1.0 - (do not remove this comment)
(script# 125)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rm125 0
)

(local
	[sewage 3]
)
(instance rm125 of Room
	(properties
		picture 201
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(ego
			view:
				(cond 
					(wearingGasMask (if gunDrawn 306 else 296))
					(gunDrawn 6)
					(else 0)
				)
			x:
				(switch prevRoomNum
					(123
						(if (<= (ego x?) 70) 165 else 237)
					)
					(126 305)
					(128
						(if (>= (ego x?) 235) 140 else 35)
					)
				)
			y:
				(switch prevRoomNum
					(123
						(if (<= (ego x?) 70) 100 else 95)
					)
					(126
						(if (<= (ego y?) 115) 95 else 135)
					)
					(128 180)
				)
			init:
		)
		(HandsOn)
		(if (!= prevRoomNum 123) (= methaneGasTimer 70))
		(if
			(and
				(== prevRoomNum 123)
				(>= (ego x?) 70)
				(>= methaneGasTimer 20)
			)
			(= methaneGasTimer (- methaneGasTimer 10))
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 1
			cel: 0
			posn: 260 128
			setPri: 1
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 1
			cel: 2
			posn: 317 130
			setPri: 1
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 3
			cel: 0
			posn: 176 141
			setPri: 1
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(if (< howFast 30) ([sewage 0] stopUpd:))
		(sewerLight posn: 127 78 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 226 56 ignoreActors: 1 init: stopUpd:)
		(cockroach
			setLoop: 0
			ignoreActors:
			init:
			setMotion: MoveTo 147 50 cockroach
		)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((>= (ego x?) 315)
				(if (not wearingGasMask)
					(Print 125 0)
				else
					(Print 125 1)
				)
				(= methaneGasTimer -1)
				(= wearingGasMask 0)
				(curRoom newRoom: 126)
			)
			((>= (ego y?) 190)
				(cond 
					((> methaneGasTimer 60) 0)
					((not wearingGasMask) (Print 125 0))
					(else (Print 125 1))
				)
				(= methaneGasTimer -1)
				(= wearingGasMask 0)
				(curRoom newRoom: 128)
			)
			((ego inRect: 175 85 250 94) (curRoom newRoom: 123))
		)
		(super doit:)
	)
	
			(method (handleEvent event)
	
						(cond
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emRIGHT_BUTTON))
				)


					(if	(ClickedInRect 3 35 185 189 event) ;left up
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 12 190 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
					(if	(ClickedInRect 113 144 185 189 event) ;left down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 126 193 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
				
					(if	(ClickedInRect 316 319 91 104 event) ;up down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 325 98 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
						
					(if	(ClickedInRect 315 319 131 139 event) ;down right
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 322 135 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
						
		

















		
					(if (ClickedOnObj ego (event x?) (event y?)) ;clicked on ego mask
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
;;;									(Print 205 28)
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
				
			)
						)
						
		)
	
	
	
	
	
	
	
)

(instance cockroach of Actor
	(properties
		y 108
		x 147
		yStep 1
		view 260
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if
		(or (event claimed?) (!= (event type?) evSAID))
			(return)
		)
		(if (Said '/bug>')
			(cond 
				((>= (cockroach x?) 200) (Print 125 2) (event claimed: 1))
				((Said 'look') (Print 125 3))
				((Said 'get') (Print 125 4))
			)
		)
	)
	
	(method (cue)
		(self posn: 0 1000)
	)
)

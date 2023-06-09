;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	pipe
	[sewage 3]
	atDeadEnd
	nearDrainPipe
)
(instance rm130 of Room
	(properties
		picture 201
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(HandsOn)
		(ego
			;view: (if (not gunDrawn) 0 else 6)
			view:
				(cond 
					(wearingGasMask (if gunDrawn 306 else 296))
					(gunDrawn 6)
					(else 0)
				)
			x:
				(switch prevRoomNum
					(128
						(if (<= (ego x?) 70) 165 else 237)
					)
					(131 310)
				)
			y:
				(switch prevRoomNum
					(128
						(if (<= (ego x?) 70) 100 else 95)
					)
					(131
						(if (<= (ego y?) 115) 100 else 135)
					)
				)
			init:
		)
		((= pipe (Prop new:))
			view: 92
			loop: 0
			cel: 0
			posn: 286 117
			setPri: 3
			ignoreActors: 1
			init:
			stopUpd:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 1
			cel: 2
			posn: 317 130
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 3
			cel: 0
			posn: 176 141
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 3
			cel: 1
			posn: 133 173
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60) ([sewage 0] stopUpd:))
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(sewerRat
			name: 2
			setLoop: 3
			illegalBits: 0
			posn: 249 65
			ignoreActors: 1
			init:
			setMotion: MoveTo 400 65 sewerRat
		)
		(= nearDrainPipe 0)
		(sewerLight posn: 127 78 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 226 56 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((>= (ego x?) 315) (curRoom newRoom: 131))
			((ego inRect: 175 85 250 94) (curRoom newRoom: 128))
			((ego inRect: 0 190 150 200)
				(if (not atDeadEnd)
					(= atDeadEnd 1)
					(Print 130 0)
					(ego setMotion: MoveTo (ego x?) 180)
				)
			)
			(
			(and (ego inRect: 280 92 290 108) (not nearDrainPipe))
				(= nearDrainPipe 1)
				(if (== (Random 0 4) 3)
					(= sewerCutscene 1)
					(ego setScript: drainPipeScript)
				)
			)
			(else (= nearDrainPipe 0) (= atDeadEnd 0))
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

		
					(if	(ClickedInRect 313 319 90 103 event) ;right up
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 325 97 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
					(if	(ClickedInRect 311 319 131 138 event) ;right down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 325 135 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
				
					(if	(ClickedInRect 7 35 184 189 event) ;left down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 12 202 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
						
					(if	(ClickedInRect 113 145 186 189 event) ;down right
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 128 200 self)
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

(instance drainPipeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
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
				(Timer setCycle: self 10)
			)
			(3
				(Print 130 1)
				(Print 130 2)
				(Print 130 3)
				(ego dispose:)
				(EgoDead 130 4)
			)
		)
	)
)

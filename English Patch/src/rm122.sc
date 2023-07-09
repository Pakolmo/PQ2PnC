;;; Sierra Script 1.0 - (do not remove this comment)
(script# 122)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rm122 0
)

(local
	[sewage 4]
)
(instance rm122 of Room
	(properties
		picture 200
		style $0000
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 301)
		(self setRegions: 205)
		(if (== prevRoomNum 121)
			(= methaneGasTimer 180) ;80
		else
			(= methaneGasTimer -1)
		)
		(ego
			view:
				(cond 
					(wearingGasMask (if gunDrawn 306 else 296))
					(gunDrawn 6)
					(else 0)
				)
			x:
				(cond 
					((== prevRoomNum 121) 15)
					((<= (ego x?) 85) 190)
					(else 285)
				)
			y:
				(if (== prevRoomNum 121)
					(if (<= (ego y?) 115) 95 else 135)
				else
					185
				)
			init:
		)
		(HandsOn)
		((View new:)
			view: 301
			loop: 0
			cel: 0
			posn: 112 90
			setPri: 1
			init:
			addToPic:
		)
		((Prop new:)
			view: 260
			loop: 8
			cel: 1
			posn: 311 182
			setPri: 1
			ignoreActors: 1
			init:
			addToPic:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 2
			cel: 2
			posn: 210 189
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 65 124
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 16 126
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 3] (Prop new:))
			view: 99
			loop: 2
			cel: 0
			posn: 163 153
			setPri: 0
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
		(sewerLight posn: 190 78 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 91 54 ignoreActors: 1 init: stopUpd:)
		(cockroach
			setLoop: 0
			ignoreActors:
			init:
			setMotion: MoveTo 165 50 cockroach
		)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((< (ego y?) 170) (if (== methaneGasTimer -1) (= methaneGasTimer 80))) ;80)))
			((== methaneGasTimer -1) 0)
			(else
				(cond 
					((>= methaneGasTimer 79) 0) ;60) 0)
					((not wearingGasMask) (Print 122 0))
					(else (Print 122 1))
				)
				(ego view: (if (not gunDrawn) 0 else 6))
				(= methaneGasTimer -1)
				(= wearingGasMask 0)
			)
		)
		(cond 
			((>= (ego y?) 190) (curRoom newRoom: 124))
			((<= (ego x?) 3)
				(cond 
					((> methaneGasTimer 79) 0) ;60) 0)
					((not wearingGasMask) (Print 122 0))
					(else (Print 122 1))
				)
				(= wearingGasMask 0)
				(= methaneGasTimer -1)
				(curRoom newRoom: 121)
			)
		)
		(super doit:)
	)
)

(instance cockroach of Actor
	(properties
		y 92
		x 165
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
				((>= (cockroach x?) 200) (Print 122 2) (event claimed: 1))
				((Said 'look') (Print 122 3))
				((Said 'get') (Print 122 4))
			)
		)
		

	
						(cond
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emRIGHT_BUTTON))
				)
		
							(if	(ClickedInRect 1 4 90 103 event) ;left up
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo -4 98 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
					(if	(ClickedInRect 1 3 131 138 event) ;left down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo -5 134 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
				
					(if	(ClickedInRect 283 319 91 189 event) ;up right
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 306 208 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
						
					(if	(ClickedInRect 172 207 186 189 event) ;down left
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 190 196 self)
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
	
	(method (cue)
		(self posn: 0 1000)
	)
)

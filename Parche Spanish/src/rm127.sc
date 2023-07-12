;;; Sierra Script 1.0 - (do not remove this comment)
(script# 127)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rm127 0
)

(local
	[sewage 4]
)
(instance rm127 of Room
	(properties
		picture 200
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
					(124
						(if (<= (ego x?) 200) 80 else 155)
					)
					(126 15)
					(129
						(if (<= (ego x?) 85) 190 else 285)
					)
				)
			y:
				(switch prevRoomNum
					(124 95)
					(126
						(if (<= (ego y?) 115) 95 else 135)
					)
					(129 185)
				)
			init:
		)
		(HandsOn)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 2
			cel: 2
			posn: 210 189
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
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
			cycleSpeed: 3
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
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		(if (< howFast 60) ([sewage 0] stopUpd:))
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(sewerRat
			name: 4
			setLoop: 2
			illegalBits: 0
			posn: 70 66
			ignoreActors: 1
			init:
			setMotion: MoveTo -100 66 sewerRat
		)
		(sewerLight posn: 190 78 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 91 54 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((< (ego y?) 155)
				(if (!= methaneGasTimer -1)
					(cond 
						((> methaneGasTimer 60) 0)
						((not wearingGasMask) (Print 127 0))
						(else (Print 127 1))
					)
					(ego view: (if (not gunDrawn) 0 else 6))
					(= wearingGasMask 0)
					(= methaneGasTimer -1)
				)
			)
			((== methaneGasTimer -1) (= methaneGasTimer 65))
		)
		(cond 
			(sewerCutscene 0)
			((>= (ego y?) 190) (curRoom newRoom: 129))
			((<= (ego x?) 5) (curRoom newRoom: 126))
			((ego inRect: 70 70 165 94) (curRoom newRoom: 124))
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
	
	
	
	
	
	
	
	
	
	
	
	
)

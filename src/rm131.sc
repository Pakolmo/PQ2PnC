;;; Sierra Script 1.0 - (do not remove this comment)
(script# 131)
(include sci.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm131 0
)

(local
	door
	bains
	[sewage 5]
)
(instance bainsShot of Sound
	(properties
		number 41
		priority 10
	)
)

(instance rm131 of Room
	(properties
		picture 76
		style $0000
	)
	
	(method (init)
		(self setRegions: 205)
		(super init:)
		((= door (AutoDoor new:))
			doorControl: 4096
			entranceTo: 133
			facingLoop: 3
			view: 293
			loop: 0
			posn: 184 82
			setPri: 1
			stopUpd:
			init:
		)
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
				(130 10)
				(133 180)
				(132 310)
			)
			y:
				(cond 
					((== prevRoomNum 133) 85)
					((<= (ego y?) 115) 100)
					(else 140)
				)
			init:
		)
		(HandsOn)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 319 131
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
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
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 120 131
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 3] (Prop new:))
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
		((= [sewage 4] (Prop new:))
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
			([sewage 2] stopUpd:)
		)
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 3] stopUpd:)
			([sewage 4] stopUpd:)
		)
		(sewerLight posn: 167 54 ignoreActors: 1 init: stopUpd:)
		(if untiedMarie
			(Load rsVIEW 13)
			(Load rsVIEW 15)
			((= bains (Actor new:))
				view: 13
				posn: 110 100
				loop: 0
				cel: 0
				init:
				setCycle: Walk
				setScript: bainsKillsScript
			)
		)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego x?) 5) (curRoom newRoom: 130))
			((>= (ego x?) 315) (curRoom newRoom: 132))
			((== (door doorState?) 2)
				(ego heading: 0 setMotion: MoveTo 180 10)
				(curRoom newRoom: 133)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 301)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '[<around][/(!*,chamber,sewer)]') (Print 131 0) (Print 131 1))
					((Said 'look/door') (Print 131 2))
					((Said 'open/door') (Print 131 3))
					((Said '*/door') (Print 131 4))
				)
			)
		)
		
		
		
	

	
	
	
						(cond
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emRIGHT_BUTTON))
				)

				(if (ClickedOnObj door (event x?) (event y?)) ;clicked on door
						(event claimed: TRUE)
						(switch theCursor
							(998
								(Print 131 2)
							)
							(995
								(Print 131 3)
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

(instance bainsKillsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bains setMotion: MoveTo 130 100 self)
				(HandsOff)
			)
			(1
				(bains view: 15 loop: 0 cel: 0 setCycle: EndLoop self)
				(bainsShot play:)
			)
			(2
				(ego
					view: 297
					illegalBits: 0
					x: 184
					loop: 3
					setCycle: EndLoop self
				)
			)
			(3 (EgoDead 131 5))
		)
	)
)

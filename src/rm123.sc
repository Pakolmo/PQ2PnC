;;; Sierra Script 1.0 - (do not remove this comment)
(script# 123)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rm123 0
)

(local
	[sewage 3]
)
(instance rm123 of Room
	(properties
		picture 204
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(ego
			x:
				(cond 
					((== prevRoomNum 120) (if (<= (ego x?) 50) 185 else 270))
					((<= (ego x?) 200) 26)
					(else 158)
				)
			y: (if (== prevRoomNum 120) 87 else 185)
			;view: (if (not gunDrawn) 0 else 6)
			view:
				(cond 
					(wearingGasMask (if gunDrawn 306 else 296))
					(gunDrawn 6)
					(else 0)
				)
			init:
		)
		(HandsOn)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 3
			cel: 2
			posn: 121 188
			setPri: 0
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 3
			cel: 0
			posn: 185 144
			setPri: 0
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 3
			cel: 0
			posn: 225 113
			setPri: 0
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(if (< howFast 30) ([sewage 0] stopUpd:))
		(sewerLight posn: 160 60 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 245 28 ignoreActors: 1 init: stopUpd:)
		(cockroach
			setLoop: 0
			ignoreActors:
			init:
			setMotion: MoveTo 133 44 cockroach
		)
	)
	
	(method (doit)
		(cond 
			((< (ego y?) 100)
				(if (!= methaneGasTimer -1)
					(= methaneGasTimer -1)
					(if (not wearingGasMask)
						(Print 123 0)
					else
						(Print 123 1)
						(= wearingGasMask 0)
						(ego view: (if (not gunDrawn) 0 else 6))
					)
				)
			)
			((== methaneGasTimer -1) (= methaneGasTimer 130)) ;65
		)
		(cond 
			(sewerCutscene 0)
			((<= (ego y?) 85) (curRoom newRoom: 120))
			((>= (ego y?) 190) (curRoom newRoom: 125))
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
		y 120
		x 133
		yStep 1
		view 260
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(evSAID
				(if (Said '/bug>')
					(cond 
						((> (cockroach x?) 200) (Print 123 2) (event claimed: 1))
						((Said 'look') (Print 123 3))
						((Said 'get,apprehend') (Print 123 4))
					)
				)
			)
		)
	)
	
	(method (cue)
		(self posn: 0 1000)
	)
)

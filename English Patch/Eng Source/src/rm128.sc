;;; Sierra Script 1.0 - (do not remove this comment)
(script# 128)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm128 0
)

(local
	climbedUpLadder
	triedToOpenManhole
	bainsAmbush
	bains
	[sewage 3]
)
(instance bainsMusic of Sound
	(properties
		number 10
		priority 8
	)
)
(instance ladder of View
)
(instance bainsGunFire of Sound
	(properties
		number 41
		priority 9
	)
)

(instance rm128 of Room
	(properties
		picture 204
		style $0000
	)
	
	(method (init)
		(Load rsVIEW 295)
		(Load rsVIEW 15)
		(Load rsVIEW 298)
		(super init:)
		(self setRegions: 205)
		(ego
			x:
				(cond 
					((== prevRoomNum 125) (if (<= (ego x?) 50) 197 else 270))
					((<= (ego x?) 200) 26)
					(else 158)
				)
			y: (if (== prevRoomNum 125) 87 else 185)
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
;;;		((View new:)
		(ladder
			view: 295
			loop: 4
			cel: 0
			posn: 143 105
			ignoreActors: 1
			init:
			addToPic:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 3
			cel: 2
			posn: 121 188
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
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
			cycleSpeed: 2
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
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([sewage 0] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(if (< howFast 30) ([sewage 1] stopUpd:))
		(sewerRat
			name: 3
			setLoop: 5
			illegalBits: 0
			posn: 72 95
			ignoreActors: 1
			init:
			setMotion: MoveTo -100 167 sewerRat
		)
		(sewerLight
			posn: 164 60
			ignoreActors: 1
			setPri: 14
			init:
			stopUpd:
		)
		(sewerLight2 posn: 245 28 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego y?) 85) (curRoom newRoom: 125))
			((>= (ego y?) 190) (curRoom newRoom: 130))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look/ladder') (Print 128 0))
					((Said 'open,remove,press,move/cover[<manhole]')
						(if (not climbedUpLadder)
							(Print 128 1)
						else
							(Print 128 2)
							(= triedToOpenManhole 1)
						)
					)
					((Said 'climb[/ladder]>')
						(cond 
							((Said '<down')
								(if (not climbedUpLadder)
									(Print 128 3)
								else
									(ego setScript: ladderScript)
									(ladderScript changeState: 3)
								)
							)
							((Said '<up')
								(cond 
									((!= climbedUpLadder 0) (Print 128 4))
									((& (ego onControl: 1) $0080)
										(ego setScript: ladderScript)
										(ladderScript changeState: 1)
									)
									(else (NotClose))
								)
							)
							((Said '[<!*]')
								(cond 
									(climbedUpLadder
										(ego setScript: ladderScript)
										(ladderScript changeState: 3)
									)
									((& (ego onControl: 1) $0080) (ladderScript changeState: 1))
									(else (NotClose))
								)
							)
						)
					)
				)
			)
		)
		
		
		
		
					(cond
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emRIGHT_BUTTON))
				)
				
				
						(if	(ClickedInRect 2 40 183 189 event) ;left up
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 15 192 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
					(if	(ClickedInRect 142 160 183 189 event) ;left down
				(event claimed: TRUE)
					(switch theCursor
						(999 ;walk
									(ego setMotion: MoveTo 146 194 self)
						)
						
							(else
								(event claimed: FALSE)
							 )
						)
					)
				

			
		
		
					
				(if (ClickedOnObj ladder (event x?) (event y?)) ;clicked on ladder
											(event claimed: TRUE)
						(switch theCursor
						
							(998
								(Print 128 0)
							)
							(995
								
							(if (not climbedUpLadder)
;;;									(Print 128 3)
								else
									(ego setScript: ladderScript)
									(ladderScript changeState: 3)
								)
								(cond 
									((!= climbedUpLadder 0) (Print 128 4))
									((& (ego onControl: 1) $0080)
										(ego setScript: ladderScript)
										(ladderScript changeState: 1)
									)
									(else (NotClose))
								)
						)
						(996
								(cond 
									(climbedUpLadder
										(ego setScript: ladderScript)
										(ladderScript changeState: 3)
									)
									((& (ego onControl: 1) $0080) (ladderScript changeState: 1))
									(else (NotClose))
								)
						)
							(else
								(event claimed: FALSE)
							 )
						)
					)
					
																			

	

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
		
		
		
	)
)

(instance ladderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(= climbedUpLadder 1)
				(= gunDrawn 0)
				(ego
					view: 295
					loop: 0
					cel: 0
					illegalBits: 0
					ignoreActors: 1
					posn: 143 105
					setCycle: CycleTo 11 1 self
					setMotion: 0
				)
			)
			(2
				(Print 128 5)
				(User canInput: FALSE)
			)
			(3
				(ego loop: 2 cel: 0 setCycle: CycleTo 7 1 self)
				(User canInput: FALSE)
			)
			(4
				(HandsOn)
				(ego
					view: (if (not gunDrawn) 0 else 6)
					posn: 171 102
					loop: 2
					cel: 0
					setCycle: Walk
					illegalBits: -32768
					ignoreActors: 0
				)
				(= climbedUpLadder 0)
				(self changeState: 0)
				(if (and (not bainsAmbush) triedToOpenManhole)
					(= triedToOpenManhole 0)
					(= bainsAmbush 1)
					(Load rsSOUND 41)
					(bainsMusic play:)
					((= bains (Actor new:))
						view: 14
						setLoop: 0
						cel: 0
						illegalBits: 0
						ignoreActors:
						posn: -20 222
						setStep: 6 3
						init:
						setCycle: Walk
						setScript: ambushScript
					)
					(ego setScript: 0)
				)
			)
		)
	)
)

(instance ambushScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 30)))
			(1
				(if isHandsOff
					(= cycles 4)
					(-- state)
				else
					(HandsOff)
					(bains setMotion: MoveTo 20 205 self)
					(ego setCycle: 0)
				)
			)
			(2
				(bains view: 15 cel: 0 setCycle: EndLoop self)
				(bainsGunFire play:)
			)
			(3
				(if (or gunDrawn (>= (Random 0 10) 5))
					(self changeState: 4)
				else
					(self changeState: 10)
				)
			)
			(4
				(ego
					illegalBits: 0
					view: 298
					loop: 0
					posn: (- (ego x?) 5) (ego y?)
				)
				(bains view: 13)
				(RedrawCast)
				(EgoDead 128 6)
			)
			(10
				(bains
					view: 14
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo -20 222 self
				)
			)
			(11
				(bainsMusic stop:)
				(= cycles 4)
			)
			(12
				(HandsOn)
				(ego setMotion: 0 setCycle: Walk)
				(Print 128 7)
				(bains dispose:)
			)
		)
	)
)

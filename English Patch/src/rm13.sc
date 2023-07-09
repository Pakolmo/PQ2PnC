;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
;(include sci.sh)
(include system.sh)
(include keys.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm13 0
	ExitWorkCar 1
)
(synonyms
	(console dash)
)

(local
	[str 100]
	local100
	local101
	onTheRoad
	outsideRoom
	extraView
	local105
	local106
	local107
	local108
	windshieldOverlay
	local110
	textColor
	local112
	local113
	local114
	local115
	local116
	local117
	local118
	local119
	local120
	local121
)

(procedure (drive)
	(if
		(and
			(== gamePhase 3)
			(Btst fBeenAtMallCrimeScene)
		)
		(= gamePhase 4)
	)
	(switch driveDest
		(14 ;Lytton Airport
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)
				(if (!= outsideRoom 14)
					(= outsideRoom 14)
					(sequencer changeState: 85)
					(= local107 0)
					(= local108 62)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)
				(= local101
					(if (== driveFix 14) (>= global169 2) else 0)
				)
			)
		)
		(25 ;753 Third Street -inn
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(if
					(and
						(== gamePhase 12)
						(not (Btst 165))
					)
					(Bset 165)
				)
				(User canInput: 0)
				(if (!= outsideRoom 25)
					(= outsideRoom 25)
					(sequencer changeState: 85)
					(= local107 40)
					(= local108 100)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)												
				(= local101
					(if (== driveFix 14)
						(>= global169 2)
					else
						0
					)
				)
			)
		)
		(29 ;Arnie's cafe
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)
				(if (!= outsideRoom 29)
					(= outsideRoom 29)
					(sequencer changeState: 85)
					(= local107 80)
					(= local108 100)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)
				(= local101
					(if (== driveFix 14)
						(>= global169 2)
					else 
						0
					)
				)
			)
		)
		(67 ;oak tree mall
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)
				(if (!= outsideRoom 67)
					(= outsideRoom 67)
					(sequencer changeState: 85)
					(= local107 100)
					(= local108 100)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)
				(= local101
					(if (== driveFix 14)
						(>= global169 2)
					else
						0
					)
				)	
			)		
		)
		(61 ;cotton cove
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)	
				(if (!= outsideRoom 61)
					(= outsideRoom 61)
					(sequencer changeState: 85)
					(= local107 122)
					(= local108 12)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)
				(= local101
					(if (== driveFix 14)
						(>= global169 2)
					else
						0
					)
				)	
			)						
		)
		(22 ;Jail								
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(if (!= outsideRoom 22)
					(= outsideRoom 22)
					(sequencer changeState: 85)
					(= local107 90)
					(= local108 10)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)
				(= local101
					(if (== driveFix 14) (>= global169 2) else 0)
				)
			)												
		)
		(31 ;west peach
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)											
				(if (!= outsideRoom 31)
					(= outsideRoom 31)
					(sequencer changeState: 85)
					(= local107 30)
					(= local108 60)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)
				)											
				(= local101
					(if (== driveFix 14) (>= global169 2) else 0)
				)	
			)
		)
		(27 ;west rose								
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)																			
				(cond 
					((!= gamePhase 10)
						(LocPrint 1 13 51)
						(User canInput: FALSE)
					)
					((!= outsideRoom 27)
						(= outsideRoom 27)
						(sequencer changeState: 85)
						(= local107 62)
						(= local108 50)
					)
					(else
						(if onTheRoad (Print 13 49) else (Print 13 50))
						(User canInput: FALSE)
					)
				)						
				(= local101
					(if (== driveFix 14) (>= global169 2) else 0)
				)	
			)	
		)						
		(1  ;the office
			(if (not (ego has: 3))
				(Print 13 48)
			else
				(User canInput: 0)
				(if (!= outsideRoom 1)
					(= outsideRoom 1)
					(sequencer changeState: 85)
					(= local107 110)
					(= local108 40)
				else
					(if onTheRoad (Print 13 49) else (Print 13 50))
					(User canInput: FALSE)			
				)											
				(= local101
					(if (== driveFix 14) (>= global169 2) else 0)
				)	
			)		
		)
	)
)

(procedure (ExitWorkCar)
	(if
		(and
			(== windshieldOverlay local107)
			(== local110 local108)
		)
		(NormalEgo)
		(Print 13 0)
		(if (and (== outsideRoom 25) (Btst fDroveToMotel)) ;165
			(= outsideRoom 225)
		)
		(NormalEgo)
		(curRoom newRoom: outsideRoom)
		(if (== theCursor 991) ;reset cursor from exit to walk
			(theGame setCursor: 999 (HaveMouse))
		)
	else
		(Print 13 1)
	)
)

(procedure (localproc_005b)
	(lines startUpd:)
	(extraScene hide:)
	(rightScene startUpd:)
	(leftScene startUpd:)
	(= onTheRoad 1)
	(= roomCarParked 13)
	(Bset fKeithFollows) ;40
	(= local116 300)
	(= local117 20)
)

(procedure (localproc_0097)
	(= local118 0)
	(= local112 0)
	(DrawCel 180 4 0 4 145 15)
	(Display 13 2 ;"---------"
		p_width 316
		p_at 0 158
		p_color 0
		p_back 0
		p_font 0
	)
)

(procedure (LocPrint param1 param2 param3)
	(localproc_0097)
	(= local118 80)
	(= local112 1)
	(DrawCel 180 param1 0 4 145 15)
	(= textColor 15)
	(if (== argc 2)
		(Display (Format @str param2)
			p_width 316
			p_at 0 158
			p_color textColor
			p_font 0
		)
	else
		(Display (Format @str param2 param3)
			p_width 316
			p_at 0 158
			p_color textColor
			p_font 0
		)
	)
)

(instance hand of View
	(properties)
)

(instance cig of Prop
	(properties)
)

(instance puff of Prop
	(properties)
)

(instance rightScene of Prop
	(properties)
)

(instance leftScene of Prop
	(properties)
)

(instance extraScene of Prop
	(properties)
)

(instance lines of Prop
	(properties)
)

(instance scanner of Prop
	(properties)
)

(instance sonny of PicView
	(properties
		y 105
		x 63
		view 71
		priority 13
	)
)

(instance keithBud of PicView
	(properties
		y 107
		x 247
		view 71
		cel 1
		priority 13
	)
)

(instance sShoulder of PicView
	(properties
		y 141
		x 99
		view 71
		cel 4
		priority 13
	)
)

(instance kShoulder of PicView
	(properties
		y 137
		x 208
		view 71
		cel 5
		priority 13
	)
)

(instance wheel of PicView
	(properties
		y 130
		x 113
		view 71
		cel 2
		priority 12
	)
)

(instance street of PicView
	(properties
		y 101
		x 154
		view 71
		loop 5
		priority 1
	)
)

(instance rm13 of Room
	(properties
		picture 13
		style $0000
	)
	
	(method (init)
;		(HandsOff)
		(User canInput: FALSE)
		(Load VIEW 71)
		(Load VIEW 200)
		(Load VIEW 180)
		(Load SOUND 5)
		(= currentCar carWork)
		(= onTheRoad 0)
		(= gunDrawn 0)
		(if (not (== prevRoomNum 166))
			(= driveFix prevRoomNum)	
		)
		(= outsideRoom driveFix)
		(= windshieldOverlay
			(switch driveFix ;driveFix
				(14 0)
				(25 40)
				(225 40)
				(29 80)
				(67 100)
				(61 122)
				(22 90)
				(31 30)
				(27 62)
				(1 110)
			)
		)
		(= local108
			(= local110
				(switch driveFix ;driveFix
					(14 62)
					(25 100)
					(29 100)
					(67 100)
					(61 12)
					(22 10)
					(31 60)
					(27 50)
					(1 40)
				)
			)
		)
		(= local107 windshieldOverlay)
		(= extraView
			(switch windshieldOverlay
				(0 329)
				(40 332)
				(80 331)
				(100 328)
				(122 326)
				(90 334)
				(30 333)
				(62 330)
				(110 327)
			)
		)
		(Load VIEW extraView)
		(= local116 300)
		(ego
			setPri: 0
			setLoop: -1
			setCel: -1
			posn: 0 0
			setMotion: 0
			ignoreActors: 0
			illegalBits: -32768
			init:
		)
		(hand
			view: 71
			posn: 129 149
			loop: 0
			cel: 3
			setPri: 13
			init:
			stopUpd:
		)
		(lines
			view: 71
			posn: 149 101
			setPri: 2
			setLoop: 4
			setCycle: Forward
			init:
			stopUpd:
		)
		(leftScene
			view: 200
			posn: 114 93
			setPri: 0
			setLoop: 0
			cel: 1
			setCycle: Forward
			init:
			stopUpd:
		)
		(rightScene
			view: 200
			posn: 201 93
			setPri: 0
			setLoop: 1
			setCycle: Forward
			init:
			stopUpd:
		)
		(extraScene
			view: extraView
			setLoop: 0
			setCel: 0
			posn: 160 100
			setPri: 3
			setLoop: 0
			init:
			stopUpd:
		)
		(StatusLine enable:)
		(super init:)
		(self setScript: rm13Script)
		(if (== prevRoomNum 166)
			(drive)	
		)
		;(Printf {gamePhase: %d} gamePhase)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?)
			(return)
		else
			(switch (event type?)
				(mouseDown ;evMOUSEBUTTON
					(if local112
						(event claimed: 1)
						(localproc_0097)
						(if (not local113)
							(sequencer cue:)
						)
					else
						(event claimed: 0)
					)
				)
			)
		)
	)
)

(instance rm13Script of Script
	(properties)
	
	(method (doit)
		(curRoom setRegions: 950)
		(cond 
			((and local112 (== local118 1))
				(localproc_0097)
				(if (not local113)
					(sequencer cue:)
				)
			)
			((and local112 (> local118 0))
				(-- local118)
			)
		)
		(if
			(and
				(or (== global159 1) (== captainWarningTimer 1))
				(not local115)
			)
			(= local115 1)
			(sequencer changeState: 105)
		)
		(if (> local121 1)
			(-- local121)
		)
		(if (== local121 1)
			(= local121 0)
			(self changeState: 1)
		)
		(if onTheRoad
			(if (> howFast 20)
				(cond 
					((<= (hand y?) 148)
						(hand posn: 129 (+ (hand y?) 1))
					)
					((>= (hand y?) 154)
						(hand posn: 129 (- (hand y?) 1))
					)
					(else
						(hand posn: 129 (+ (hand y?) (Random -2 2)))
					)
				)
			)
			(if (not local112)
				(if (> local117 1)
					(-- local117)
				)
				(cond 
					((> windshieldOverlay local107)
						(-- windshieldOverlay)
					)
					((< windshieldOverlay local107)
						(++ windshieldOverlay)
					)
					((> local110 local108)
						(-- local110)
					)
					(else
						(if local101
							(EgoDead
								{As a police officer, you should not abandon any stolen property which you have recovered. Next time, think about what you're doing.}
;;;								{Como agente de polic|a, no debes abandonar ning{n objeto robado que hayas recuperado. La pr/xima vez, piensa en lo que haces.}
							)
						)
						(if (< local110 local108)
							(++ local110)
						else
							(User canInput: 0)
							(if onTheRoad
								(if (not local119)
									(sequencer changeState: 91)
									(= local119 1)
								)
								(if local113
									(if (and (== outsideRoom 25) (Btst 165))
										(= outsideRoom 225)
									)
									(if
										(and
											(== outsideRoom 61)
											(not (Btst 19))
											(== gamePhase 5)
										)
										(sequencer changeState: 8)
									)
									(NormalEgo)
									(cSound loop: 1 fade:)
									(NormalEgo)
									(curRoom newRoom: outsideRoom)
								)
							)
						)
					)
				)
			)
		else
			(lines stopUpd:)
			(rightScene stopUpd:)
			(leftScene stopUpd:)
			(if (> local116 0)
				(-- local116)
			)
			(if (and (== local116 1) (not local112))
				(= local116 200)
				(cond 
					((Btst fCanGetWarrant)
						(LocPrint 1 13 3)
					)
					((Random 0 1)
						(LocPrint 1 13 4)
					)
					(else
						(LocPrint 1 13 5)
					)
				)
			)
		)
		(if (== local117 1)
			(= local117 0)
			(cond 
				((and (not (Btst 15)) (== gamePhase 1))
					(if (== outsideRoom 22)
						(sequencer changeState: 1)
					else
						(= local117 15)
					)
				)
				((and (not (Btst 16)) (== gamePhase 2))
					(sequencer changeState: 74)
					(= local117 20)
				)
				((and (Btst 16) (not (Btst 17)))
					(sequencer changeState: 5)
				)
				((and (not local100) (== gamePhase 4))
					(sequencer changeState: 79)
					(= local117 25)
				)
				((and (not (Btst 21)) (== gamePhase 6))
					(sequencer changeState: 36)
				)
				(
					(and
						(not (Btst 22))
						(== gamePhase 6)
						(== driveFix 14)
					)
					(sequencer changeState: 53)
					(= local117 25)
				)
				(
				(and (Btst 22) (ego has: 11) (not (Btst 23)))
					(sequencer changeState: 54)
				)
				((and (not (Btst 24))
						(== gamePhase 10)) (sequencer changeState: 57))
				(
				(and (not (Btst fKidnappingReported)) global127 (== driveFix 31)) (sequencer changeState: 63))
				((and (not (Btst 28)) (Btst 52)) (sequencer changeState: 68))
			)
			(if (not local117)
				(if
					(not
						(if (> 20 (- local110 local108))
							(> (- local110 local108) -20)
						)
					)
					(= local110 (+ local108 16))
				)
				(if
					(not
						(if (> 20 (- windshieldOverlay local107))
							(> (- windshieldOverlay local107) -20)
						)
					)
					(= windshieldOverlay (+ local107 16))
				)
			)
		)
		(super doit:)
		(if (== theCursor 999)
			(theGame setCursor: 991 (HaveMouse))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(addToPics
					add: sonny keithBud sShoulder kShoulder wheel street
					doit:
				)
				(scanner
					view: 71
					posn: 159 141
					cycleSpeed: 1
					setCycle: Forward
					setLoop: 6
					setPri: 13
					init:
					setScript: sequencer
				)
				(if (< howFast 30)
					(scanner stopUpd: setScript: 0)
				)
				(cig
					view: 71
					posn: 215 81
					setLoop: 2
					cycleSpeed: 1
					setCycle: Forward
					setPri: 12
					init:
				)
				(puff
					view: 71
					posn: 205 97
					cycleSpeed: 2
					setCycle: Forward
					setLoop: 3
					setPri: 13
					init:
				)
				(if (< howFast 30)
					(cig stopUpd:)
					(puff posn: 1000 1000 stopUpd:)
				)
				(self cue:)
			)
			(1
				(if (< howFast 30)
					(self cue:)
				else
					(puff setCycle: EndLoop self)
				)
			)
			(2 
				(= local121 (Random 20 70))
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown ;evKEYBOARD
				(if
					(or
						(== (= temp0 (event message?)) KEY_F6)
						(== temp0 KEY_F8)
						(== temp0 KEY_F10)
					)
					(Print 13 6)
					(event claimed: 1)
				else
					(event claimed: 0)
				)
				(if (== temp0 13)
					(if local112
						(event claimed: 1)
						(localproc_0097)
						(if (not local113) (sequencer cue:))
					else
						(event claimed: 0)
					)
				)
				(event claimed: (not local113))
			)
			(saidEvent
				(cond 
					((Said '/too<i') (Print 13 7))
					(
						(or
							(Said 'chat[/friend,dude]')
							(Said 'cease/friend,dude,cigarette')
							(Said '/cigarette')
						)
						(sequencer changeState: 113)
					)
					((Said 'check,look/gauge<gas,gas') (Print 13 8))
					((Said 'look,read/note,list') (Print 13 9))
					(
						(or
							(Said 'begin[/auto,ignition,engine]')
							(Said 'turn/key')
							(Said 'turn<on/engine,auto')
						)
						(Print 13 10)
					)
					(
						(or
							(Said 'cease[/auto,engine]')
							(Said 'turn<off/engine,auto')
							(Said 'switch<off/engine,auto')
						)
						(if (== local107 windshieldOverlay)
							(Print 13 11)
						else
							(Print 13 12)
						)
					)
					((Said '/box,compartment') (Print 13 13))
					((Said '/siren,(3<code)') (Print 13 14))
					((Said '/lamp,light[<read]') (Print 13 15))
					((Said 'look>')
						(cond 
							((Said '/speedometer,speed') (if onTheRoad (Print 13 16) else (Print 13 17)))
							((or (Said '/bench<back') (Said '/back')) (Print 13 18))
							((Said '/light[<read]') (Print 13 19))
							((Said '/tray,ashtray') (Print 13 20))
							((Said 'look/pane')
								(Print 13 21)
								(if onTheRoad
									(Print 13 22)
								else
									(switch windshieldOverlay
										(0 (Print 13 23))
										(40 (Print 13 24))
										(80 (Print 13 25))
										(100 (Print 13 26))
										(122 (Print 13 27))
										(90 (Print 13 28))
										(30 (Print 13 29))
										(62 (Print 13 30))
										(110 (Print 13 31))
										(else  (Print 13 32))
									)
								)
							)
							((Said '/mirror') (Print 13 33))
							((Said '/console') (Print 13 34))
							((Said '/extender,scanner') (Print 13 35))
							((Said '/note,clipboard') (Print 13 36))
							((Said '/cigarette,pack') (Print 13 37))
							(
								(Said
									'/building,ave,air,cloud,sun,barn,home,airplane,airport,lot,fence,cove,inn,bush,tree,garage,sign,mall,store,cafe,warehouse'
								)
								(Print 13 38)
							)
							(
								(Said
									'[<at,around][/!*,auto,up,door,bonds,down,ceiling,floor,bench]'
								)
								(Print 13 39)
							)
						)
					)
					(
						(or
							(Said 'wear,fasten,use,(deposit<on),buckle/belt,belt')
							(Said 'buckle')
						)
						(Print 13 40)
					)
					((Said 'turn/extender,scanner') (Print 13 41))
					((Said 'listen/extender,scanner') (Print 13 42))
					(
						(or
							(Said 'chat,use,call/extender')
							(Said 'extender,call,finding,notify>')
						)
						(cond 
							((and (not (Btst 27)) global127) (sequencer changeState: 63))
							(bainsInCoveTimer (= bainsInCoveTimer 0) (sequencer changeState: 12))
							(
								(and
									removedBodyFromRiver
									(== gamePhase 5)
									(not (Btst fCalledCoroner))
								)
								(SolvePuzzle 2 fCalledCoroner)
								(sequencer changeState: 99)
							)
							(
								(and
									shotAtBainsInCove
									(not (Btst fRadioInCoveShooting))
								)
								(Bset fRadioInCoveShooting)
								(LocPrint 1 13 43)
							)
							(
								(and
									(or
										(Said '/warrant')
										(Said '/dispatch/warrant')
										(Said '/dispatch[/!*]')
										(Said '[/!*]')
									)
									(Btst fCanGetWarrant)
									(== windshieldOverlay 40)
								)
								(Bclr fCanGetWarrant)
								(SolvePuzzle 2)
								(= global170 200)
								(sequencer changeState: 101)
							)
							(
								(and
									(or
										(Said '/dispatch/[!*]')
										(Said '/backup,swat,team')
										(Said '/dispatch/backup,swat,team')
									)
									(Btst fCanCallForBackup)
									(== windshieldOverlay 40)
								)
								(Bclr fCanCallForBackup)
								(SolvePuzzle 2)
								(= global171 200)
								(sequencer changeState: 103)
							)
							((Btst fGotEmptyHolster)
								(sequencer changeState: 93)
							)
							((Btst fAskedWomanAboutCar)
								(sequencer changeState: 96)
							)
							((== global169 2)
								(= global169 3)
								(SolvePuzzle 2 fRadioedInStolenCarPlate)
								(sequencer changeState: 46)
							)
							(
								(and
									(Btst fGotToiletGun)
									(== gamePhase 6)
								)
								(Bclr fGotToiletGun)
								(SolvePuzzle 1)
								(sequencer changeState: 50)
							)
							(
								(and
									(Btst fReadCarRentals)
									(== gamePhase 6)
									(not (Btst fRadioedInRentalCar))
								)
								(SolvePuzzle 2 fRadioedInRentalCar)
								(LocPrint 1 13 44)
							)
							(
								(and
									(not local105)
									(or
										(and (== gamePhase 2) (== driveFix 22))
										(and (== gamePhase 3) (== driveFix 67))
										(and (== gamePhase 5) (== driveFix 61))
										(and (== gamePhase 12) (== driveFix 25))
										(and (== gamePhase 10) (== driveFix 27))
									)
								)
								(LocPrint 1 13 45)
								(= local105 1)
							)
							(else (LocPrint 1 13 46))
						)
						(event claimed: 1)
					)
					((Said 'drive/auto') (Print 13 47))
					((Said 'drive,go>')
						(if (not (ego has: 3))
							(Print 13 48)
							(event claimed: 1)
						else
							(if (and (== gamePhase 3) (Btst 152)) (= gamePhase 4))
							(if (and (== gamePhase 12) (not (Btst 165)))
								(if (Said '/inn[<snuggler,to]')
									(event claimed: 0)
								else
									(Bset 165)
								)
							)
							(User canInput: 0)
							(cond 
								(
									(or
										(Said '/cheeks')
										(Said '/home<cheeks')
										(Said '/ave<peach<lonny<222')
										(Said '/peach<lonny<222')
									)
									(if (!= outsideRoom 31)
										(= outsideRoom 31)
										(sequencer changeState: 85)
										(= local107 30)
										(= local108 60)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								(
									(or
										(Said '/home<barn')
										(Said '/office,homicide,police,barn,lpd')
										(Said '/barn,department<police[<lytton,to]')
									)
									(if (!= outsideRoom 1)
										(= outsideRoom 1)
										(sequencer changeState: 85)
										(= local107 110)
										(= local108 40)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
										
									)
								)
								(
									(or
										(Said '/jail,courthouse')
										(Said '/center<enforcement<law')
									)
									(if (!= outsideRoom 22)
										(= outsideRoom 22)
										(sequencer changeState: 85)
										(= local107 90)
										(= local108 10)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								((Said '/cove[<cotton,to]')
									(if (!= outsideRoom 61)
										(= outsideRoom 61)
										(sequencer changeState: 85)
										(= local107 122)
										(= local108 12)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								((Said '/airport[<lytton,to]')
									(if (!= outsideRoom 14)
										(= outsideRoom 14)
										(sequencer changeState: 85)
										(= local107 0)
										(= local108 62)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								(
								(or (Said '/inn[<snuggler,to]') (Said '/ave<third<753'))
									(if (!= outsideRoom 25)
										(= outsideRoom 25)
										(sequencer changeState: 85)
										(= local107 40)
										(= local108 100)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								(
								(or (Said '/cafe[<arnie,to]') (Said '/arnie,date,chow'))
									(if (!= outsideRoom 29)
										(= outsideRoom 29)
										(sequencer changeState: 85)
										(= local107 80)
										(= local108 100)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								(
									(or
										(Said '/area<death>')
										(Said '/death[<area,to]>')
										(Said '/warehouse>')
										(Said '/district<warehouse>')
										(Said '/ave<rose<lonny<160')
										(Said '/rose<lonny<160')
									)
									(cond 
										(
											(and
												(or (Said '/area<death') (Said '/death'))
												(!= gamePhase 10)
											)
											(LocPrint 1 13 51)
											(User canInput: FALSE)
										)
										((!= outsideRoom 27)
											(event claimed: 1)
											(= outsideRoom 27)
											(sequencer changeState: 85)
											(= local107 62)
											(= local108 50)
										)
										(else
											(if onTheRoad (Print 13 49) else (Print 13 50))
											(event claimed: 1)
											(User canInput: FALSE)
										)
									)
								)
								(
									(or
										(Said '/mall[<(tree<fig),to]')
										(Said '/tree<fig')
										(Said '/center,mall<shopping')
									)
									(if (!= outsideRoom 67)
										(= outsideRoom 67)
										(sequencer changeState: 85)
										(= local107 100)
										(= local108 100)
									else
										(if onTheRoad (Print 13 49) else (Print 13 50))
										(User canInput: FALSE)
									)
								)
								((Said '/ave<fig<5556') (Print 13 52) (User canInput: FALSE))
								(
									(or
										(Said '/castle<caffeine[<carol,to]')
										(Said '/willie<drunk')
										(Said '/chamber<blue')
										(Said '/delphoria[<hotel,to]')
									)
									(User canInput: FALSE)
									(Print 13 53)
								)
								((Said '/steelton,houston,coarsegold') (User canInput: FALSE) (Print 13 54))
								((Said '/[*]') (User canInput: FALSE) (Print 13 55))
								(else (User canInput: FALSE) (event claimed: 1) (Print 13 56))
							)
						)
						(= local101
							(if (== driveFix 14) (>= global169 2) else 0)
						)
					)
					((Said 'chase[/bains]')
						(if bainsInCoveTimer
							(= outsideRoom 14)
							(sequencer changeState: 11)
							(= local107 0)
							(= local108 62)
						else
							(LocPrint 1 13 57)
						)
					)
					(
						(or
							(Said 'cease<chase/bains')
							(Said 'call<off/chase,pursuit')
							(Said 'cease/chase,pursuit')
						)
						(if local114
							(= outsideRoom 61)
							(sequencer changeState: 34)
							(= local107 122)
							(= local108 12)
						)
					)
					(
						(or
							(Said 'exit[/auto]')
							(Said 'open/door')
							(Said 'get<out')
						)
						(ExitWorkCar)
					)
					((or (Said 'gave/cheeks') (Said '//cheeks'))
						(if (== (sequencer state?) 55)
							(sequencer changeState: 56)
						else
							(event claimed: 0)
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
				(if (ClickedInRect 140 180 136 143 event) ;radio
					(if
						(or 
							(== theCursor 995) ;hand
							(== theCursor 996) ;mouth
						)
						(cond 
							(
								(and
									(not (Btst 27))
									global127
								)
								(sequencer changeState: 63)
							)
							(bainsInCoveTimer
								(= bainsInCoveTimer 0)
								(sequencer changeState: 12)
							)
							(
								(and
									removedBodyFromRiver
									(== gamePhase 5)
									(not (Btst fCalledCoroner))
								)
								(SolvePuzzle 2 fCalledCoroner)
								(sequencer changeState: 99)
							)
							(
								(and
									shotAtBainsInCove
									(not (Btst fRadioInCoveShooting))
								)
								(Bset fRadioInCoveShooting)
								(LocPrint 1 13 43)
							)
							(
								(and
;;;									(or
;;;										(Said '/warrant')
;;;										(Said '/dispatch/warrant')
;;;										(Said '/dispatch[/!*]')
;;;										(Said '[/!*]')
;;;									)
									(Btst fCanGetWarrant)
									(== windshieldOverlay 40)
								)
								(Bclr fCanGetWarrant)
								(SolvePuzzle 2)
								(= global170 200)
								(sequencer changeState: 101)
							)
							(
								(and
;;;									(or
;;;										(Said '/dispatch/[!*]')
;;;										(Said '/backup,swat,team')
;;;										(Said '/dispatch/backup,swat,team')
;;;									)
									(Btst fCanCallForBackup)
									(== windshieldOverlay 40)
								)
								(Bclr fCanCallForBackup)
								(SolvePuzzle 2)
								(= global171 200)
								(sequencer changeState: 103)
							)
							((Btst fGotEmptyHolster)
								(sequencer changeState: 93)
							)
							((Btst fAskedWomanAboutCar)
								(sequencer changeState: 96)
							)
							((== global169 2)
								(= global169 3)
								(SolvePuzzle 2 fRadioedInStolenCarPlate)
								(sequencer changeState: 46)
							)
							(
								(and
									(Btst fGotToiletGun)
									(== gamePhase 6)
								)
								(Bclr fGotToiletGun)
								(SolvePuzzle 1)
								(sequencer changeState: 50)
							)
							(
								(and
									(Btst fReadCarRentals)
									(== gamePhase 6)
									(not (Btst fRadioedInRentalCar))
								)
								(SolvePuzzle 2 fRadioedInRentalCar)
								(LocPrint 1 13 44)
							)
							(
								(and
									(not local105)
									(or
										(and (== gamePhase 2) (== driveFix 22))
										(and (== gamePhase 3) (== driveFix 67))
										(and (== gamePhase 5) (== driveFix 61))
										(and (== gamePhase 12) (== driveFix 25))
										(and (== gamePhase 10) (== driveFix 27))
									)
								)
								(LocPrint 1 13 45)
								(= local105 1)
							)
							(else (LocPrint 1 13 46))
						)
						(event claimed: 1)
					)		
				)
;;;				(if (ClickedInRect 99 117 108 119 event) ;speedometer
;;;					(event claimed: TRUE)
;;;					(switch theCursor				
;;;						(998 ;look					
;;;							(if onTheRoad (Print 13 16) else (Print 13 17))
;;;						)
;;;						(else
;;;							(event claimed: FALSE)
;;;						)
;;;					)
;;;				)
				
				(if (ClickedInRect 144 159 110 126 event) ;List
					(event claimed: TRUE)
					(switch theCursor				
						(998 ;look				
							(Print 13 9)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(if
					(or
						(== theCursor 999) ;walk anywhere to exit
						(== theCursor 991) ;salir
					)
					(ExitWorkCar)
				)
				(if 
					(or
						(ClickedOnPicView kShoulder (event x?) (event y?)) ;kShoulder keithBud
						(ClickedOnPicView keithBud (event x?) (event y?))
					)
						(event claimed: TRUE)
						(switch theCursor	
							(996 ; talk keith smoke
								(sequencer changeState: 113)
							)
							(998 ; look keith
								
								
							)
							(else
								(event claimed: TRUE)	
							)
						)
				)
				(if
					(and 
						(ClickedInRect 97 137 97 120 event) ;wheel
						(== (event claimed?) FALSE)
					)
					(event claimed: TRUE)
					(switch theCursor
						(995 ;hand on wheel to drive
							(if (ego has: iUnmarkedCarKeys)
								(cSound loop: 1 fade:) ;prevent sound bug
								(theGame newRoom: 166)
							else
								(Print {You don't have the keys for this car.})
;;;								(Print {No tienes las llaves de este coche.})
							)
						)	
						(103 ;unmarked keys drive. 
							(cSound loop: 1 fade:) ;prevent sound bug
							(theGame newRoom: 166)
						 )
					)						
				)
			)
		)
	)
)	


(instance sequencer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local113 1))
			(1
				(= local113 0)
				(Bset 15)
				(LocPrint 1 13 58)
			)
			(2 (LocPrint 1 13 59))
			(3 (LocPrint 1 13 60))
			(4
				(LocPrint 1 13 61)
				(= local113 1)
			)
			(5
				(= local113 0)
				(Bset 17)
				(= gamePhase 3)
				(= captainWarningTimer 300)
				(= isOnDuty 2)
				(LocPrint 1 13 62)
			)
			(6 (LocPrint 1 13 63))
			(7
				(LocPrint 1 13 64)
				(= local113 1)
			)
			(8
				(= local113 0)
				(Bset 19)
				(LocPrint 1 13 65)
				(if
				(Print 13 66 67 -1 128 81 {You're ON!} 1 81 {No way.} 0)
;;;				(Print 13 66 67 -1 128 81 {=Hecho!} 1 81 {Ni hablar.} 0)
					(= local112 0)
					(Bset 114)
					(sequencer changeState: 9)
				else
					(= local112 0)
					(sequencer changeState: 10)
				)
			)
			(9
				(LocPrint 1 13 67)
				(= local113 1)
			)
			(10
				(LocPrint 1 13 68)
				(= local113 1)
			)
			(11
				(User canInput: 0)
				(Bset 20)
				(= local114 1)
				(localproc_005b)
				(cSound number: 5 loop: -1 play:)
				(self cue:)
			)
			(12
				(= local113 0)
				(Bset 20)
				(SolvePuzzle 2 164)
				(LocPrint 1 13 69)
			)
			(13 (LocPrint 0 13 70))
			(14 (LocPrint 0 13 71))
			(15 (LocPrint 1 13 72))
			(16 (LocPrint 1 13 73))
			(17 (LocPrint 0 13 74))
			(18 (LocPrint 0 13 75))
			(19 (LocPrint 1 13 76))
			(20 (LocPrint 1 13 77))
			(21 (LocPrint 0 13 78))
			(22 (LocPrint 0 13 79))
			(23 (LocPrint 0 13 80))
			(24 (LocPrint 0 13 81))
			(25 (LocPrint 0 13 82))
			(26
				(if local114
					(LocPrint 1 13 83)
				else
					(LocPrint 1 13 84)
					(= local113 1)
				)
			)
			(27 (LocPrint 2 13 85))
			(28 (LocPrint 1 13 86))
			(29 (LocPrint 2 13 87))
			(30 (LocPrint 1 13 88))
			(31
				(= gamePhase 5)
				(= captainWarningTimer 300)
				(= isOnDuty 2)
				(LocPrint 2 13 89)
			)
			(32 (LocPrint 1 13 90))
			(33
				(LocPrint 1 13 91)
				(User canInput: FALSE)
				(= local113 1)
			)
			(34
				(= local113 0)
				(= local114 0)
				(LocPrint 1 13 92)
			)
			(35
				(Format
					@str
					13
					93
					(switch outsideRoom
						(14 {Lytton Airport})
						(25 {753 Third Street})
						(29 {Arnie's restaurant})
						(67 {Oak Tree Mall})
						(61 {Cotton Cove})
						(22 {Lytton City Jail})
						(31 {222 West Peach Street})
						(27 {160 West Rose})
						(1 {the office})
						
						
;;;						(14 {Aeropuerto de Lytton})
;;;						(25 {753 de la Tercera Calle})
;;;						(29 {Restaurante de Arnie})
;;;						(67 {Centro Comercial Oak Tree})
;;;						(61 {Cotton Cove})
;;;						(22 {Prisi/n de Lytton})
;;;						(31 {222 Oeste de la Calle Peach})
;;;						(27 {160 de West Rose})
;;;						(1 {la oficina})
						
						
											
					)
				)
				(LocPrint 1 @str)
				(= local113 1)
			)
			(36
				(= local113 0)
				(Bset 21)
				(Format
					@str
					13
					94
					(switch outsideRoom
						(14 {Lytton Airport})
						(25 {753 Third Street})
						(29 {Arnie's restaurant})
						(67 {Oak Tree Mall})
						(61 {Cotton Cove})
						(22 {Lytton City Jail})
						(31 {222 West Peach Street})
						(27 {160 West Rose})
						(1 {the office})
						
;;;						(14 {Aeropuerto de Lytton})
;;;						(25 {753 de la Tercera Calle})
;;;						(29 {Restaurante de Arnie})
;;;						(67 {Centro Comercial Oak Tree})
;;;						(61 {Cotton Cove})
;;;						(22 {Prisi/n de Lytton})
;;;						(31 {222 Oeste de la Calle Peach})
;;;						(27 {160 de West Rose})
;;;						(1 {la oficina})						
						
					
						
					)
				)
				(LocPrint 0 @str)
			)
			(37 (LocPrint 0 13 95))
			(38 (LocPrint 0 13 96))
			(39 (LocPrint 0 13 97))
			(40 (LocPrint 1 13 98))
			(41 (LocPrint 0 13 99))
			(42 (LocPrint 3 13 100))
			(43 (LocPrint 0 13 101))
			(44 (LocPrint 3 13 102))
			(45
				(LocPrint 1 13 103)
				(= local113 1)
			)
			(46
				(= local113 0)
				(LocPrint 1 13 104)
			)
			(47 (LocPrint 0 13 105))
			(48 (LocPrint 0 13 106))
			(49
				(LocPrint 0 13 107)
				(if (not (Btst 49)) (= local113 1))
			)
			(50
				(= local113 0)
				(LocPrint 1 13 108)
			)
			(51 (LocPrint 0 13 109))
			(52
				(LocPrint 1 13 110)
				(= local113 1)
			)
			(53
				(Bset 22)
				(LocPrint 1 13 111)
			)
			(54
				(Bset 23)
				(= local113 0)
				(LocPrint 1 13 112)
			)
			(55
				(LocPrint 1 13 113)
				(= local113 1)
			)
			(56 (LocPrint 1 13 114))
			(57
				(Bset 24)
				(LocPrint 1 13 115)
			)
			(58
				(= local106 0)
				(= local113 0)
				(Bset 25)
				(LocPrint 1 13 116)
			)
			(59
				(if (not local106) (cSound number: 5 loop: -1 play:))
				(LocPrint 0 13 117)
				(localproc_005b)
			)
			(60 (LocPrint 1 13 118))
			(61 (LocPrint 1 13 119))
			(62
				(LocPrint 1 13 120)
				(= local113 1)
				(User canInput: FALSE)
			)
			(63
				(User canInput: 0)
				(= local113 0)
				(Bset fKidnappingReported)
				(LocPrint 1 13 121)
			)
			(64
				(if (== outsideRoom 1)
					(LocPrint 1 13 122)
				else
					(self cue:)
				)
			)
			(65 (LocPrint 1 13 123))
			(66 (LocPrint 0 13 124))
			(67
				(LocPrint 1 13 125)
				(= local113 1)
				(User canInput: FALSE)
			)
			(68
				(= local113 0)
				(Bset 28)
				(LocPrint 1 13 126)
			)
			(69 (LocPrint 1 13 127))
			(70 (LocPrint 1 13 128))
			(71 (LocPrint 1 13 129))
			(72 (LocPrint 1 13 130))
			(73 (self changeState: 67))
			(74
				(= local113 0)
				(Bset 16)
				(LocPrint 0 13 131)
			)
			(75 (LocPrint 1 13 132))
			(76 (LocPrint 0 13 133))
			(77 (LocPrint 0 13 134))
			(78
				(LocPrint 1 13 135)
				(= local113 1)
			)
			(79
				(= local113 0)
				(LocPrint 0 13 136)
				(= local100 1)
			)
			(80 (LocPrint 1 13 137))
			(81 (LocPrint 0 13 138))
			(82
				(= gamePhase 5)
				(= captainWarningTimer 300)
				(= isOnDuty 2)
				(LocPrint 0 13 139)
			)
			(83
				(LocPrint 1 13 140)
				(= local113 1)
			)
			(85
				(HandsOff) ;add
				(if (== global169 3) (= global169 0))
				(Bclr 51)
				(Bclr 50)
				(Bset 111)
				(if
					(and
						(== driveFix 61)
						(== gamePhase 5)
						(!= outsideRoom 61)
					)
					(= gamePhase 6)
					(= global169 1)
					(= captainWarningTimer 0)
				)
				(if
					(and
						(== driveFix 27)
						(== gamePhase 10)
						(!= captainWarningTimer 1)
					)
					(= gamePhase 11)
				)
				(cond 
					(
						(and
							(not (Btst 25))
							(== gamePhase 11)
							(ego has: 24)
							(== outsideRoom 25)
						)
						(sequencer changeState: 58)
					)
					(local114 (self changeState: 34))
					(
						(and
							(== windshieldOverlay local107)
							(== local110 local108)
						)
						(self cue:)
					)
					(else (self changeState: 89))
				)
				(HandsOn) ;add
			)
			(86
				(= local113 0)
				(switch outsideRoom
					(14 (LocPrint 1 13 141))
					(25 (LocPrint 1 13 142))
					(29 (LocPrint 1 13 143))
					(67 (LocPrint 1 13 144))
					(61 (LocPrint 1 13 145))
					(22 (LocPrint 1 13 146))
					(31
						(if (>= gamePhase 8)
							(LocPrint 1 13 147)
						else
							(LocPrint 1 13 148)
						)
					)
					(1 (LocPrint 1 13 149))
					(27 (LocPrint 1 13 150))
				)
			)
			(87
				(Format
					@str
					{Keith grabs the mike and calls dispatch... "Dispatch...53mary2 is 10-8 from %s."}
;;;					{Keith agarra el micr/fono y llama al despacho... "Despacho...53mary2 en 10-8 desde %s."}					
					(switch windshieldOverlay
						(0 {Lytton Airport})
						(40 {753 Third Street})
						(80 {Arnie's restaurant})
						(100 {Oak Tree Mall})
						(122 {Cotton Cove})
						(90 {Lytton City Jail})
						(30 {222 West Peach Street})
						(62 {160 West Rose})
						(110 {the office})


;;;						(0 {Aeropuerto de Lytton})
;;;						(40 {753 de la Tercera Calle})
;;;						(80 {Restaurante de Arnie})
;;;						(100 {Centro Comercial Oak Tree})
;;;						(122 {Cotton Cove})
;;;						(90 {Prisi/n de Lytton})
;;;						(30 {222 Oeste de la Calle Peach})
;;;						(62 {160 de West Rose})
;;;						(110 {la oficina})
					)
				)
				(LocPrint 1 @str)
			)
			(88
				(Format
					@str
					13
					151
					(switch windshieldOverlay
						(0 {Lytton Airport})
						(40 {753 Third Street})
						(80 {Arnie's restaurant})
						(100 {Oak Tree Mall})
						(122 {Cotton Cove})
						(90 {Lytton City Jail})
						(30 {222 West Peach Street})
						(62 {160 West Rose})
						(110 {the office})



;;;						(0 {Aeropuerto de Lytton})
;;;						(40 {753 de la Tercera Calle})
;;;						(80 {Restaurante de Arnie})
;;;						(100 {Centro Comercial Oak Tree})
;;;						(122 {Cotton Cove})
;;;						(90 {Prisi/n de Lytton})
;;;						(30 {222 Oeste de la Calle Peach})
;;;						(62 {160 de West Rose})
;;;						(110 {la oficina})
					)
				)
				(LocPrint 0 @str)
				(cSound number: 5 loop: -1 play:)
				(= local106 1)
				(localproc_005b)
			)
			(89
				(if (not local106) (cSound number: 5 loop: -1 play:))
				(= local106 0)
				(= local113 0)
				(Format
					@str
					13
					152
					(switch outsideRoom
						(14 {Lytton Airport})
						(25 {753 Third Street})
						(29 {Arnie's restaurant})
						(67 {Oak Tree Mall})
						(61 {Cotton Cove})
						(22 {Lytton City Jail})
						(31 {222 West Peach Street})
						(27 {160 West Rose})
						(1 {the office})

;;;						(14 {Aeropuerto de Lytton})
;;;						(25 {753 de la Tercera Calle})
;;;						(29 {Restaurante de Arnie})
;;;						(67 {Centro Comercial Oak Tree})
;;;						(61 {Cotton Cove})
;;;						(22 {Prisi/n de Lytton})
;;;						(31 {222 Oeste de la Calle Peach})
;;;						(27 {160 de West Rose})
;;;						(1 {la oficina})
					)
				)
				(LocPrint 1 @str)
			)
			(90
				(Format
					@str
					13
					153
					(switch local107
						(0 {Lytton Airport})
						(40 {753 Third Street})
						(80 {Arnie's restaurant})
						(100 {Oak Tree Mall})
						(122 {Cotton Cove})
						(90 {Lytton City Jail})
						(30 {222 West Peach Street})
						(62 {160 West Rose})
						(110 {the office})
						
						
;;;						(0 {Aeropuerto de Lytton})
;;;						(40 {753 de la Tercera Calle})
;;;						(80 {Restaurante de Arnie})
;;;						(100 {Centro Comercial Oak Tree})
;;;						(122 {Cotton Cove})
;;;						(90 {Prisi/n de Lytton})
;;;						(30 {222 Oeste de la Calle Peach})
;;;						(62 {160 de West Rose})
;;;						(110 {la oficina})						
					)
				)
				(LocPrint 0 @str)
				(User canInput: FALSE)
				(= local113 1)
			)
			(91
				(= local113 0)
				(Format
					@str
					(if
						(or
							(and
								(== outsideRoom 67)
								(or (< gamePhase 2) (>= gamePhase 4))
							)
							(and
								(== outsideRoom 61)
								(or (< gamePhase 4) (== gamePhase 6))
							)
							(and (== outsideRoom 31) (< gamePhase 8))
							(== outsideRoom 1)
						)
						{"Dispatch...53Mary2 10-7 at %s."}
;;;						{"Despacho...53Mary2 10-7 en %s."}
					else
						{Keith contacts dispatch..."Dispatch...53mary2 10-97 %s."}
;;;						{Keith contactando con despacho..."Despacho...53mary2 10-97 desde %s."}
					)
					(switch outsideRoom
						(14 {Lytton Airport})
						(25 {753 Third Street})
						(29 {Arnie's restaurant})
						(67 {Oak Tree Mall})
						(61 {Cotton Cove})
						(22 {Lytton City Jail})
						(31 {222 West Peach Street})
						(27 {160 West Rose})
						(1 {the office})
						
;;;						(14 {Aeropuerto de Lytton})
;;;						(25 {753 de la Tercera Calle})
;;;						(29 {Restaurante de Arnie})
;;;						(67 {Centro Comercial Oak Tree})
;;;						(61 {Cotton Cove})
;;;						(22 {Prisi/n de Lytton})
;;;						(31 {222 Oeste de la Calle Peach})
;;;						(27 {160 de West Rose})
;;;						(1 {la oficina})						
					)
				)
				(LocPrint 1 @str)
			)
			(92
				(Format
					@str
					13
					154
					(switch outsideRoom
						(14 {Lytton Airport})
						(25 {753 Third Street})
						(29 {Arnie's restaurant})
						(67 {Oak Tree Mall})
						(61 {Cotton Cove})
						(22 {Lytton City Jail})
						(31 {222 West Peach Street})
						(27 {160 West Rose})
						(1 {the office})
						
;;;						(14 {Aeropuerto de Lytton})
;;;						(25 {753 de la Tercera Calle})
;;;						(29 {Restaurante de Arnie})
;;;						(67 {Centro Comercial Oak Tree})
;;;						(61 {Cotton Cove})
;;;						(22 {Prisi/n de Lytton})
;;;						(31 {222 Oeste de la Calle Peach})
;;;						(27 {160 de West Rose})
;;;						(1 {la oficina})						
					)
				)
				(LocPrint 0 @str)
				(= local113 1)
			)
			(93
				(= local113 0)
				(Bclr 50)
				(SolvePuzzle 1)
				(LocPrint 1 13 155)
			)
			(94 (LocPrint 0 13 156))
			(95
				(LocPrint 0 13 157)
				(if (not (Btst 51)) (= local113 1))
			)
			(96
				(= local113 0)
				(SolvePuzzle 2)
				(Bclr 51)
				(LocPrint 1 13 158)
			)
			(97 (LocPrint 0 13 159))
			(98
				(LocPrint 0 13 160)
				(= local113 1)
			)
			(99
				(= local113 0)
				(LocPrint 1 13 161)
			)
			(100
				(LocPrint 0 13 162)
				(= local113 1)
			)
			(101
				(= local113 0)
				(LocPrint 1 13 163)
			)
			(102
				(LocPrint 0 13 164)
				(= local113 1)
			)
			(103
				(= local113 0)
				(LocPrint 1 13 165)
			)
			(104
				(LocPrint 0 13 166)
				(= local113 1)
			)
			(105 (= seconds 3))
			(106
				(User canInput: 0)
				(= local113 0)
				(LocPrint 0 13 167)
			)
			(107 (LocPrint 1 13 132))
			(108 (LocPrint 2 13 168))
			(109
				(if (== gamePhase 10)
					(= global159 1)
					(= captainWarningTimer 0)
				)
				(if global159
					(cSound loop: 1 fade:)
					(NormalEgo)
					(curRoom newRoom: 300)
				else
					(= global159 600)
					(if (and (!= gamePhase 0) (!= 0 8))
						(LocPrint 2 13 169)
					else
						(LocPrint 2 13 170)
					)
				)
			)
			(110 (LocPrint 1 13 171))
			(111
				(if (and (!= gamePhase 0) (!= 0 8))
					(LocPrint 1 13 172)
				else
					(LocPrint 1 13 173)
				)
			)
			(112
				(= local113 1)
				(if (and (!= gamePhase 8) (!= gamePhase 0))
					(= global159 300)
				)
				(NormalEgo)
				(cSound loop: 1 fade:)
				(curRoom newRoom: 300)
			)
			(113
				(= local113 0)
				(switch (Random 0 1)
					(0 (LocPrint 1 13 174))
					(1 (LocPrint 1 13 175))
				)
			)
			(114
				(switch (Random 0 3)
					(0 (LocPrint 1 13 176))
					(1 (LocPrint 1 13 177))
					(2 (LocPrint 1 13 178))
					(3 (LocPrint 1 13 179))
				)
				(if (Print 13 66 81 {YES!!} 0 81 {no} 1 67 -1 128)
;;;				(if (Print 13 66 81 {==S|!!} 0 81 {no} 1 67 -1 128)					
					(LocPrint 1 13 180)
					(= local113 1)
				else
					(= local118 1)
				)
			)
			(115
				(switch (Random 0 15)
					(0 (LocPrint 1 13 181))
					(1 (LocPrint 1 13 182))
					(2 (LocPrint 1 13 183))
					(3 (LocPrint 1 13 184))
					(4 (LocPrint 1 13 185))
					(5 (LocPrint 1 13 186))
					(6 (LocPrint 1 13 187))
					(7 (LocPrint 1 13 188))
					(8 (LocPrint 1 13 189))
					(9 (LocPrint 1 13 190))
					(10 (LocPrint 1 13 191))
					(11 (LocPrint 1 13 192))
					(12 (LocPrint 1 13 193))
					(13 (Format @str 13 194))
					(14 (Format @str 13 195))
					(15 (Format @str 13 196))
				)
				(= local113 1)
			)
		)
	)
)

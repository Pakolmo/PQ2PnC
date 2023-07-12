;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm3 0
)
(synonyms
	(steve jones)
	(cop detective)
	(dooley lieutenant)
)

(local
	talkedToSteve
	talkedToLloyd
	;colbyFile
	;dickeyFile
	;jonesFile
	;simmsFile
	;sniderFile
	;snowFile
	;valanciaFile
	;drawerLabel
	selectedFile
	theFile
	readingFile
	kimChat
)

(instance drawerLabel of Actor
	(properties)
)
(instance colbyFile of Actor
	(properties)
)
(instance dickeyFile of Actor
	(properties)
)
(instance jonesFile of Actor
	(properties)
)
(instance simmsFile of Actor
	(properties)
)
(instance sniderFile of Actor
	(properties)
)
(instance snowFile of Actor
	(properties)
)
(instance valanciaFile of Actor
	(properties)
)

(procedure (localproc_1132)
	(switch theFile
		(0
			(mugShot
				loop: 2
				cel: 0
				init:
			)
			(RedrawCast)
			(Display 3 67
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 68
				p_at 20 65
				p_width 280
			)
		)
		(1
			(mugShot
				loop: 1
				cel: 2
				init:
			)
			(RedrawCast)
			(Display 3 69
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 70
				p_at 20 65
				p_width 300
			)
		)
		(2
			(mugShot
				loop: 0
				cel: 2
				init:)
			(RedrawCast)
			(Display 3 71
				p_at 120 10
				p_width 180
				p_font 0)
			(Display 3 72
				p_at 20 65
				p_width 300
			)
		)
		(3
			(mugShot
				loop: 0
				cel: 0
				init:
			)
			(RedrawCast)
			(Display 3 73
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 74
				p_at 20 65
				p_width 300
			)
		)
		(4
			(mugShot
				loop: 0
				cel: 1
				init:
			)
			(RedrawCast)
			(Display 3 75
				p_at 120 10
				p_width 200
			)
			(Display 3 76
				p_at 20 65
				p_width 300
			)
		)
		(5
			(mugShot
				loop: 1
				cel: 0
				init:
			)
			(RedrawCast)
			(Display 3 77
				p_at 120 10
				p_width 200
			)
			(Display 3 78
				p_at 20 65
				p_width 300
			)
		)
		(6
			(mugShot
				loop: 1
				cel: 1
				init:
			)
			(RedrawCast)
			(Display 3 79
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 80
				p_at 20 65
				p_width 300
			)
		)
	)
)

;(instance Kim of Feature
(instance Kim of Actor
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 100 134 141 156))
				(if (Said '/(kim,broad)')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 0)
			)
			((Said 'look/kim,broad,cop')
				(Print 3 1)
			)
			(
				(or
					(Said 'chat/kim,broad,cop')
					(Said '/hello')
				)
				(switch (Random 0 2)
					(0
						(Print 3 2)
					)
					(1
						(Print 3 3)
					)
					(else
						(Print 3 4)
					)
				)
			)
			((Said '*/kim,broad')
				(switch (Random 0 2)
					(0
						(Print 3 5)
					)
					(1
						(Print 3 6)
					)
					(2
						(Print 3 7)
					)
				)
			)
			((Said '[ask][/help]')
				(switch (Random 0 2)
					(0
						(Print 3 8)
					)
					(1
						(Print 3 9)
					)
					(2
						(Print 3 10)
					)
				)
			)
			((Said 'ask')
				(Print 3 11)
			)
		)
	)
)

(instance Pratt of Actor ;Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 38 130 100 156))
				(if (Said '/lloyd')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 12)
			)
			((>= gamePhase 8)
				(if (Said '/lloyd,dude,cop')
					(Print 3 13)
				else
					(return)
				)
			)
			((Said 'look/lloyd,dude,cop')
				(Print 3 14)
			)
			(
				(or
					(Said 'help/lloyd,dude')
					(Said 'chat,ask/lloyd,dude/investigation,cocaine,(complaint<cocaine)')
					(Said'chat,ask/investigation,cocaine,(complaint<cocaine)')
				)
				(cond 
					((== lloydInRehab 1)
						(Print 3 15)
					)
					((Btst fLearnedAboutAddiction)
						(Print 3 16)
						(Print 3 17)
						(Print 3 18)
						(Print 3 19)
						(Print 3 20)
						(SolvePuzzle 5 85)
						(= lloydInRehab 1)
					)
					(else
						(Print 3 21)
					)
				)
			)
			((Said 'ask')
				(Print 3 22)
			)
			(
				(or (Said 'chat/lloyd,dude,cop') (Said '/hello'))
				(cond 
					((== lloydInRehab 1) (Print 3 23))
					((not talkedToLloyd)
						(= talkedToLloyd 1)
						(Print 3 24)
						(switch (Random 0 2)
							(0 (Print 3 25))
							(1 (Print 3 26))
							(2 (Print 3 27))
						)
					)
					(else (Print 3 28))
				)
			)
		)
	)
)

(instance Poet of Actor ;Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 168 134 240 156))
				(if (Said '/steve')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 29)
			)
			((Said 'look/steve,dude,cop')
				(Print 3 30)
			)
			(
				(or
					(Said 'chat,ask/steve,dude,cop')
					(Said '/hello')
				)
				(switch talkedToSteve
					(0
						(Print 3 31)
						(= talkedToSteve 1)
					)
					(1
						(Print 3 32)
					)
					(else
						(Print 3 33)
					)
				)
			)
			(
				(or
					(and (== talkedToSteve 1) (Said 'affirmative'))
					(Said 'ask,tell,listen/poem,steve')
				)
				(switch (Random 0 3)
					(0
						(Print 3 34)
					)
					(1
						(Print 3 35)
					)
					(2
						(Print 3 36)
					)
					(3
						(Print 3 37)
					)
				)
				(= talkedToSteve 2)
			)
			((Said 'ask')
				(Print 3 11)
			)
			((Said 'affirmative')
				(Print 3 38)
			)
			((Said 'no')
				(if (== talkedToSteve 1)
					(Print 3 39)
					(= talkedToSteve 0)
				else
					(Print 3 38)
				)
			)
		)
	)
)

(instance Dooley of Actor ;Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 158 120 220 134))
				(if (Said '/dooley')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 40)
			)
			((Said 'look/dooley,dude,cop')
				(Print 3 41)
			)
			(
				(or
					(Said 'chat/dooley,dude,cop')
					(Said '/hello')
				)
				(switch (Random 0 2)
					(0
						(Print 3 42)
					)
					(1
						(Print 3 43)
					)
					(else
						(Print 3 44)
					)
				)
			)
			((Said 'ask')
				(Print 3 45)
			)
			((Said 'help')
				(if (Said '/cabinet,file')
					(Print 3 46)
				else
					(Print 3 47)
				)
			)
		)
	)
)

;;;(instance Computer of Actor ;Feature
;;;	(properties)
;;;	
;;;	(method (handleEvent event)
;;;		(cond 
;;;			(
;;;				(or
;;;					(event claimed?)
;;;					(!= (event type?) saidEvent)
;;;					(!= (curRoom script?) rm3Script)
;;;				)
;;;				(return)
;;;			)
;;;			(
;;;				(not
;;;					(if (ego inRect: 75 120 120 130)
;;;					else
;;;						(& (ego onControl:) cYELLOW) ;$e000
;;;					)
;;;				)
;;;				(if (Said '/computer')
;;;					(NotClose)
;;;				else
;;;					(event claimed: 0)
;;;				)
;;;			)
;;;			((Said 'look/desk')
;;;				(Print 3 48)
;;;			)
;;;			((Said 'turn<on/computer')
;;;				(Print 3 49)
;;;			)
;;;			((Said 'look,use/computer')
;;;				(curRoom newRoom: 8)
;;;			)
;;;		)
;;;	)
;;;)

(instance rm3 of Room
	(properties
		picture 3
		style HWIPE
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 61)
		(super init:)
		(ego
			posn:
				(if (== prevRoomNum 8)120 else 180)
				(if (== prevRoomNum 8) 120 else 172)
			view:
				(cond 
					((== prevRoomNum 8) (if (not gunDrawn) 0 else 6))
					((not gunDrawn) 1)
					(else 7)
				)
		)
		;(self setFeatures: Kim Pratt Poet Dooley Computer)
		(self setLocales: 153 156)
		(= gunFireState gunPROHIBITED)
		(HandsOn)
		(curRoom setScript: rm3Script)
	)
	
	(method (dispose)
		(features eachElementDo: #dispose #delete)
		(fileScript dispose:)
		(super dispose:)
	)
)

(instance rm3Script of Script
	(properties)
	
	(method (doit)
		(if (>= (ego y?) 175)
			(curRoom newRoom: 2)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init:)
				(if (== prevRoomNum 2)
					(ego setMotion: MoveTo 180 10)
					(User prevDir: 1)
				)
				((View new:)
					view: 61
					posn: 222 117
					loop: 0
					cel: 0
					init:
					addToPic:
				)
				(Poet ;(Prop new:)
					view: 61
					posn: 194 122
					loop: 1
					cycleSpeed: 4
					setPri: 11
					setCycle: Forward
					init:
				)
				(Kim ;(View new:)
					view: 61
					posn: 135 146
					loop: 2
					cel: (Random 0 1)
					init:
					;addToPic:
				)
				(Dooley ;(View new:)
					view: 61
					posn: 178 104
					loop: 6
					cel: 0
					setPri: 9
					init:
					;addToPic:
				)
				(if (< gamePhase 8)
					(Pratt ;(View new:)
						view: 61
						posn: 61 144
						loop: 3
						cel: (Random 0 1)
						init:
						;addToPic:
					)
				)
				(if
					(and
						(>= gamePhase 8)
						(not (Btst fSteveTellsAboutLloyd))
					)
					(Bset fSteveTellsAboutLloyd)
					(if (Btst fLloydInRehab)
						(Print 3 50)
						(Print 3 51)
					else
						(Print 3 52)
						(Print 3 53)
						(Print 3 54)
					)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(switch (event type?)
			(mouseDown
				(if
					(and
						(== (event type?) evMOUSEBUTTON)
						(not (& (event modifiers?) emRIGHT_BUTTON))
					)
					(if (ClickedOnObj Kim (event x?)(event y?))	
						(event claimed: TRUE)
						(switch theCursor
							(998
								(Print 3 1)
							)
							(996 ;talk
								(if (not (ego inRect: 100 134 141 156))
									(NotClose)
								else
									(switch kimChat
										(0
											(Print 3 2)
										)
										(1
											(Print 3 3)
										)
										(2
											(Print 3 4)
										)
										(3
											(Print 3 5)
										)
										(4
											(Print 3 9)
										)
										(5
											(Print 3 10)
										)
										(6
											(Print 3 6)
										)
										(7
											(Print 3 7)
										)
										(else
											(Print 3 8)
										)
									)
									(++ kimChat)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedInRect 98 132 120 145 event) ;kims desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 3 0)
						)
					)
					(if (ClickedOnObj Pratt (event x?)(event y?))	
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(Print 3 14)
							)
							(996 ;talk
								(if (not (ego inRect: 38 130 100 156))
									(NotClose)
								else
									(if (Btst fLearnedAboutAddiction)
										(if lloydInRehab
											(Print 3 23)
										else
											(Print 3 16)
											(Print 3 17)
											(Print 3 18)
											(Print 3 19)
											(Print 3 20)
											(SolvePuzzle 5 85)
											(= lloydInRehab 1)
										)
									else
										(if (not talkedToLloyd)
											(= talkedToLloyd 1)
											(Print 3 24)
											(switch (Random 0 2)
												(0 (Print 3 25))
												(1 (Print 3 26))
												(2 (Print 3 27))
											)
										else
											(Print 3 21)
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
							(ClickedInRect 65 97 120 145 event) ;Pratt desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 3 12)
						)
					)
					(if
						(and
							(ClickedInRect 89 108 94 107 event) ;computer desk
							(== (event claimed?) FALSE)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998
								(Print 3 48)
							)
							(995
								(if (& (ego onControl:) cYELLOW) ;$e000
									(curRoom newRoom: 8)
								else
									(NotClose)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if (ClickedOnObj Poet (event x?)(event y?))	
						(event claimed: TRUE)
						(switch theCursor
							(998
								(Print 3 30)
							)
							(996
								(if (not (ego inRect: 168 134 240 156))
									(NotClose)
								else
									(if (not talkedToSteve)
										(Print 3 31)
										(++ talkedToSteve)
										(= temp0
											(PrintSpecial
												{Say:}
												;#at 10 125
												#button {Yes} 1
												#button {No} 2

;;;												{Hablar:}
;;;												;#at 10 125
;;;												#button {S|} 1
;;;												#button {No} 2


											)	
										)
										(switch temp0
											(1
												(switch (Random 0 3)
													(0
														(Print 3 34)
													)
													(1
														(Print 3 35)
													)
													(2
														(Print 3 36)
													)
													(3
														(Print 3 37)
													)
												)
											)
											(2
												(Print 3 39)
											)
										)
									else
										(Print 3 33)
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
							(ClickedInRect 177 228 121 145 event) ;poet desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 3 29)
						)
					)
					(if (ClickedOnObj Dooley (event x?)(event y?))	
						(event claimed: TRUE)
						(switch theCursor
							(998
								(Print 3 41)
							)
							(996
								(if (not (ego inRect: 158 120 220 134))
									(NotClose)
								else
									(switch (Random 0 3)
										(0
											(Print 3 42)
										)
										(1
											(Print 3 43)
										)
										(2
											(Print 3 44)
										)
										(else
											(Print 4 45)
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
							(ClickedInRect 164 207 104 123 event) ;Dooley desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 3 40)
						)
					)
					(if
						(and
							(ClickedInRect 211 235 82 129 event) ;File Cabinet
							(== (event claimed?) FALSE)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998
								(if
									(or
										(== (ego onControl: 1) cLMAGENTA) ;8192
										(== (ego onControl: 1) -24576)
									)
									(curRoom setScript: fileScript)
								else
									(NotClose)
								)
							)
							(995
								(if
									(or
										(== (ego onControl: 1) cLMAGENTA) ;8192
										(== (ego onControl: 1) -24576)
									)
									(curRoom setScript: fileScript)
								else
									(NotClose)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
			)
			(saidEvent
				(cond 
					((Said '(look,frisk)/(coat,pocket)')
						(if (ego inRect: 200 150 270 170)
							(Print 3 55)
						else
							(event claimed: 0)
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/(!*,chamber,office)]')
								(Print 3 56)
							)
							((Said '/coatrack,coatrack<coat')
								(Print 3 57)
							)
							((Said '/hat')
								(Print 3 58)
							)
						)
					)
					((Said 'get/coat,hat')
						(Print 3 59)
					)
					((Said 'open/drawer,cabinet,file')
						(if
							(or
								(== (ego onControl: 1) cLMAGENTA) ;8192
								(== (ego onControl: 1) -24576)
							)
							(curRoom setScript: fileScript)
						else
							(NotClose)
						)
					)
				)
			)
		)
	)
)

(instance mugShot of View
	(properties
		y 60
		x 75
		view 205
	)
)

(instance fileScript of Script
	(properties)
	
	(method (doit)
		(if (== theCursor 999)
			(theGame setCursor: 991 (HaveMouse))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load VIEW 205)
				;(cast eachElementDo: #dispose)
				(ego dispose:)
				(Kim dispose:)
				(Pratt dispose:)
				(Poet dispose:)
				(Dooley dispose:)
				(curRoom drawPic: 7 6)
				(drawerLabel
					view: 60
					setPri: 15
					posn: 155 113
					loop: 2
					cel: 1
					stopUpd:
					init:
				)
				(colbyFile
					view: 60
					ignoreActors:
					posn: 160 99
					loop: 1
					cel: 0
					setPri: 14
					stopUpd:
					init:
				)
				(dickeyFile
					view: 60
					ignoreActors:
					posn: 160 93
					loop: 1
					cel: 1
					setPri: 12
					stopUpd:
					init:
				)
				(jonesFile
					view: 60
					ignoreActors:
					posn: 161 87
					loop: 1
					cel: 2
					setPri: 10
					stopUpd:
					init:
				)
				(simmsFile
					view: 60
					ignoreActors:
					posn: 160 78
					loop: 1
					cel: 3
					setPri: 8
					stopUpd:
					init:
				)
				(sniderFile
					view: 60
					ignoreActors:
					posn: 160 74
					loop: 1
					cel: 4
					setPri: 6
					stopUpd:
					init:
				)
				(snowFile
					view: 60
					ignoreActors:
					posn: 161 67
					loop: 1
					cel: 5
					setPri: 4
					stopUpd:
					init:
				)
				(valanciaFile
					view: 60
					ignoreActors:
					posn: 161 61
					loop: 1
					cel: 6
					setPri: 2
					stopUpd:
					init:
				)
			)
			(1
				(= readingFile 1)
				(User canInput: 0)
				(if (< howFast 30)
					(self cue:)
				else
					(selectedFile
						setMotion: MoveTo (selectedFile x?) (- (selectedFile y?) 20) self
					)
				)
			)
			(2
				(SetMenu 513 112 0)
				(User canInput: FALSE)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 90 DISSOLVE)
				(localproc_1132)
			)
			(3
				(User canInput: 0)
				(SetMenu 513 112 1)
				(curRoom drawPic: 7 IRISIN)
				(cast eachElementDo: #show)
				(mugShot dispose:)
				(if (< howFast 30)
					(self cue:)
				else
					(selectedFile
						setMotion: MoveTo (selectedFile x?) (+ (selectedFile y?) 20) self
					)
				)
			)
			(4
				(selectedFile stopUpd:)
				(User canInput: FALSE)
				(if readingFile (self changeState: 1))
			)
			(5
				;(cast eachElementDo: #dispose)
				(drawerLabel dispose:)
				(colbyFile dispose:)
				(dickeyFile dispose:)
				(jonesFile dispose:)
				(simmsFile dispose:)
				(sniderFile dispose:)
				(snowFile dispose:)
				(valanciaFile dispose:)
				(curRoom drawPic: 3 IRISOUT)		
				(curRoom setScript: rm3Script)
				(if (== theCursor 991)
					(theGame setCursor: 999 (HaveMouse))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if ;RIGHT CLICK
					(and
						(== (event type?) evMOUSEBUTTON)
						(& (event modifiers?) emRIGHT_BUTTON)
					)
					(event claimed: TRUE)
					(switch theCursor
						(991
							(theGame setCursor: 998 (HaveMouse)) 
						)
						(998 ;hand
							(theGame setCursor: 995 (HaveMouse)) 
						)
						(995
							(theGame setCursor: 991 (HaveMouse))
						)	
						(else
							(theGame setCursor: 991 (HaveMouse)) ;default next r-click to exit
						)
					)
				)
				(if
					(and
						(== (event type?) evMOUSEBUTTON)
						(not (& (event modifiers?) emRIGHT_BUTTON))
					)
					(if 
						(and
							(ClickedOnObj colbyFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 0)
								(= selectedFile colbyFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 0)
								(= selectedFile colbyFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedOnObj dickeyFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 1)
								(= selectedFile dickeyFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 1)
								(= selectedFile dickeyFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedOnObj jonesFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 2)
								(= selectedFile jonesFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 2)
								(= selectedFile jonesFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedOnObj simmsFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 3)
								(= selectedFile simmsFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 3)
								(= selectedFile simmsFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedOnObj sniderFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 4)
								(= selectedFile sniderFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 4)
								(= selectedFile sniderFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedOnObj snowFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 5)
								(= selectedFile snowFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 5)
								(= selectedFile snowFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
						(and
							(ClickedOnObj valanciaFile (event x?) (event y?))
							(== readingFile 0)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(= theFile 6)
								(= selectedFile valanciaFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(995 ;hand
								(= theFile 6)
								(= selectedFile valanciaFile)
								(fileScript changeState: 1)
								(= readingFile 1)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(if
	        			(and
	        				(== theCursor 991) ;exit
	        				(ClickedInRect 0 320 20 190 event)
	        				(== (event claimed?) FALSE)
						) 
						(event claimed: TRUE)
						(if (== readingFile 1)	
							(Print 3 65)
							(= readingFile 0)
							(self changeState: 3)
						else
							(self changeState: 5)
						)
					)		
					(if ;change page
						(and
							(== theCursor 998) ;or look to change page
							(== readingFile 1)
							(== (event claimed?) FALSE)
						)
						(event claimed: TRUE)
						(Print 3 60 #at -1 150)
					)
					(if ;change page
						(and
							(== theCursor 995) ;hand
							(== (event claimed?) FALSE)
						)
						(if (== readingFile 1)
							(event claimed: TRUE)
							(Print 3 65)
							(= readingFile 0)
							(self changeState: 3)
						else
							(if (ClickedInRect 89 226 76 175 event) ;clicked on drawer
								(event claimed: TRUE)
								(self changeState: 5)
							)
						)
					)
				)
			)
			(saidEvent
				(cond 
					(
						(or
							(Said '[turn,go,look]/2[<page]')
							(Said '[turn,go,look]/page<next,second')
							(Said 'read,look,see,turn/page[<next,second]')
						)
						(Print 3 60 #at -1 150)
					)
					((Said 'look[<in,around][/(drawer,cabinet)]')
						(if readingFile
							(Print 3 61)
						else
							(Print 3 62)
						)
					)
					(
						(or
							(Said 'read,see,look,get,open,pull/(colby,dickey,steve,simms,snider,george,jose)>')
							(Said '/(colby,dickey,steve,simms,snider,george,jose)>')
						)
						(cond 
							(readingFile
								(event claimed: 1)
								(Print 3 63 #at -1 150 #time 7)
							)
							(
								(or
									(Said '/colby')
									(Said '/<colby')
								)
								(= selectedFile colbyFile)
								(= theFile 0)
								(self changeState: 1)
							)
							(
								(or
									(Said '/dickey')
									(Said '/<dickey')
								)
								(= selectedFile dickeyFile)
								(= theFile 1)
								(self changeState: 1)
							)
							(
								(or
									(Said '/steve')
									(Said '/<steve')
								)
								(= selectedFile jonesFile)
								(= theFile 2)
								(self changeState: 1)
							)
							(
								(or
									(Said '/simms')
									(Said '/<simms')
								)
								(= selectedFile simmsFile)
								(= theFile 3)
								(self changeState: 1)
							)
							(
								(or
									(Said '/snider')
									(Said '/<snider')
								)
								(= selectedFile sniderFile)
								(= theFile 4)
								(self changeState: 1)
							)
							(
								(or
									(Said '/george')
									(Said '/<george')
								)
								(= selectedFile snowFile)
								(= theFile 5)
								(self changeState: 1)
							)
							(
								(or
									(Said '/jose')
									(Said '/<jose')
								)
								(= selectedFile valanciaFile)
								(= theFile 6)
								(self changeState: 1)
							)
							(else
								(event claimed: 1)
								(Print 3 64 #at -1 150)
							)
						)
					)
					((Said 'exit,close,exit,close/(drawer,cabinet)[<file]')
						(if readingFile
							(Print 3 63 #at -1 150 #time 7)
						else
							(self changeState: 5)
						)
					)
					(
						(or
							(Said 'close,replace[/file]')
							(Said 'deposit[<back,away]/file')
						)
						(if readingFile
							(Print 3 65)
							(= readingFile 0)
							(self changeState: 3)
						else
							(Print 3 66 #at -1 150)
						)
					)
				)
			)
		)
	)
)

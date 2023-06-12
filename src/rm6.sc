;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
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
	rm6 0
)
(synonyms
	(cop detective)
)

(local
	local0
	numPeopleInRoom
	jamesTalk
	robertTalk
)

(instance jamesProp of Prop
	(properties)
)
(instance williamProp of Prop
	(properties)
)
(instance robertProp of Prop
	(properties)
)
(instance lauraProp of Prop
	(properties)
)

(instance rm6 of Room
	(properties
		picture 6
		style WIPEDOWN
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 68)
		(super init:)
		;(self setFeatures: Laura Robert Computer)
		(self setLocales: regFieldKit regOffice)
		(HandsOn)
		(= local0 0)
		(= numPeopleInRoom (Random 0 4))
		(= gunFireState gunPROHIBITED)
		(if (!= prevRoomNum 8)
			(User prevDir: 1)
			(ego
				posn: 87 158
				setMotion: MoveTo 87 10
			)
		)
		(ego
			view: 1
			setCycle: Walk
			illegalBits: cWHITE ;-32768
			init:
		)
		(if (<= numPeopleInRoom 2)
			(lauraProp ;(View new:) ;laura
				view: 68
				posn: 185 125
				loop: 0
				cel: 0
				init:
				addToPic:
			)
		)
		(if (!= numPeopleInRoom 0)
			(williamProp ;(View new:)
				view: 68
				posn: 206 142
				loop: 0
				cel: 1
				init:
				;addToPic:
			)
		)
		(jamesProp ;(View new:)
			view: 68
			posn: 182 148
			loop: 0
			cel: 3
			init:
			;addToPic:
		)
		(if (> numPeopleInRoom 1)
			(robertProp ;(View new:) ;Adams
				view: 68
				posn: 92 112
				loop: 0
				cel: 2
				init:
				;addToPic:
			)
		)
		(self setScript: rm6Script)
	)
	
	(method (dispose)
		(features eachElementDo: #dispose #delete)
		(super dispose:)
	)
)

(instance rm6Script of Script
	(properties)
	
	(method (doit)
		(if (> (ego y?) 160)
			(curRoom newRoom: 2)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if
					(and
						(== (event type?) evMOUSEBUTTON)
						(not (& (event modifiers?) emRIGHT_BUTTON))
					)
					(if (ClickedOnObj jamesProp (event x?) (event y?))
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(if
									(or
										(ego inRect: 124 128 192 165) ;l r t b
										(ego inRect: 193 145 240 154)
									)
									(Print 6 1) ;james is cool
								else
									(NotClose)
								)
							)
							(996 ;talk
								(if
									(or
										(ego inRect: 124 128 192 165) ;l r t b
										(ego inRect: 193 145 240 154)
									)
									(if
										(and
											global172 ;recovered gun from motel
											(< local0 2)
										)
										(switch local0
											(0
												(Print 6 8)
												(Print 6 9)
												(SolvePuzzle 2 92)
											)
											(1
												(Print 6 10)
												(Print 6 11)
												(SolvePuzzle 2 93)
											)
										)
										(++ local0)
									else
										(switch jamesTalk
											(0
												(Print 6 2) ;what can i do for you?
											)
											(1
												(Print 6 3)
											)
											(2
												(Print 6 4)
											)
											(3
												(Print 6 5)
												(Print 6 6)
											)
											(else
												(Print 6 12)
											)
										)
										(++ jamesTalk)
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
							(ClickedInRect 146 180 120 147 event) ;james desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 0)
						)
					)
					(if (ClickedOnObj williamProp (event x?) (event y?))
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(Print 6 16)
							)
							(996 ;talk 
								(if
									(or
										(ego inRect: 190 128 239 146)
										(ego inRect: 193 145 240 154)
									)
									(switch (Random 0 4)
										(0
											(Print 6 17)
										)
										(1
											(Print 6 18)
										)
										(2
											(Print 6 19)
										)
										(3
											(Print 6 20)
										)
										(4
											(Print 6 21)
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
							(or
								(ClickedInRect 248 262 117 131 event) ;computer monitor
								(ClickedInRect 238 254 125 133  event) ;computer keyboard
							)	
							(== (event claimed?) FALSE)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998
								(if (ego inRect: 193 145 240 154)
									(curRoom newRoom: 8)
								else
									(NotClose)
								)
							)
							(995
								(if (ego inRect: 193 145 240 154)
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
					(if
						(and
							(ClickedInRect 235 275 128 147 event) ;computer desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 40)
						)
					)
					(if
						(and
							(ClickedInRect 206 245 113 141 event) ;William desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 14)
						)
					)
					(if (ClickedOnObj robertProp (event x?) (event y?))
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(Print 6 25)
							)
							(996 ;talk 
								(if (ego inRect: 56 120 120 146)
									(switch robertTalk
										(0
											(Print 6 24)
											(++ robertTalk)
										)	
										(else
											(switch (Random 0 2)
												(0
													(Print 6 26)
												)
												(1
													(Print 6 27)
												)
												(2
													(Print 6 28)
													(Print 6 29)
												)
											)
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
							(ClickedInRect 108 124 123 138 event) ;trash can
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 43)
						)
					)
					(if
						(and
							(ClickedInRect 69 109 108 133 event) ;Robert desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 23)
						)
					)
					(if (ClickedOnObj lauraProp (event x?) (event y?))
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(Print 6 34)
							)
							(995 ;grope
								(if (ego inRect: 137 116 300 135)
									(switch (Random 0 2)
										(0
											(Print 6 32)
										)
										(1
											(Print 6 38)
										)
										(2
											(Print 6 39)
										)
									)
								else
									(NotClose)
								)
							)
							(996 ;talk 
								(if (ego inRect: 137 116 300 135)
									(switch (Random 0 2)
										(0
											(Print 6 35)
										)
										(1
											(Print 6 36)
										)
										(2
											(Print 6 37)
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
							(ClickedInRect 163 210 102 123 event) ;Laura desk
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 31)
						)
					)
					(if
						(and
							(ClickedInRect 181 201 77 101 event) ;poster
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 42)
						)
					)
					(if
						(and
							(ClickedInRect 32 278 25 158 event) ;anywhere else in office
							(== (event claimed?) FALSE)
						)
						(if (== theCursor 998)
							(event claimed: TRUE)
							(Print 6 44)
						)
					)
				)
			)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/flyer,painting')
								(Print 6 42)
							)
							((Said '/wastebasket,garbage')
								(Print 6 43)
							)
							((Said '[<at,around][/(!*,chamber,office)]')
								(Print 6 44)
							)
						)
					)
					((Said 'empty,clean[/newspaper,garbage,basket]')
						(Print 6 45)
					)
				)
			)
		)
	)
)

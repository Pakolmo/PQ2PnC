;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include system.sh)
(include keys.sh)
;(include sci.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm8 0
)

(local
	[str 41] ;password string
	enteringPassword
	curDir
	badPassword
	xSplit
	clsIter
	pwdChar
	pwdOffset = 128 ;start of typed passwords
)

(procedure (ToUppercase param1) ;letter to uppercase
	(return
		(if (and (<= 97 param1) (<= param1 122))
			(return (- param1 32))
		else
			(return param1)
		)
	)
)

(procedure (LocPrint) ;display file
	(Print &rest #font 7 #width 168 #at 70 18)
)

;;;(instance compCursor of Prop
;;;	(properties)
;;;)
;;;
;;;(instance fileCursor of Prop
;;;	(properties)
;;;)

(instance lite1 of View
	(properties)
)

(instance lite2 of View
	(properties)
)

(instance shaw of View
	(properties)
)

(instance rm8 of Room
	(properties
		picture 8
		style $0007
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(User canInput: 1)
		(Load VIEW 9)
		(lite1
			view: 9
			loop: 2
			cel: 0
			posn: 256 178
			init:
			stopUpd:
		)
		(lite2
			view: 9
			loop: 2
			cel: 1
			posn: 234 143
			init:
			stopUpd:
		)
		(shaw
			view: 9
			loop: 3
			cel: 0
			posn: 83 150
			init:
			addToPic:
		)
		(Format @str 8 0)
		(self setScript: rm8Script)
	)
	
	(method (dispose)
		(theGame setCursor: 995 (HaveMouse))
	)
)

(instance rm8Script of Script
	(properties)
	
	(method (handleEvent event)
		(switch (event type?)
			(evMOUSEBUTTON
				(if (& (event modifiers?) emRIGHT_BUTTON)
					(event claimed: TRUE)
					(switch theCursor
						(995 
							(theGame setCursor: 993 (HaveMouse))
						)
						(993 
							(theGame setCursor: 998 (HaveMouse))
						)
						(998 
							(theGame setCursor: 991 (HaveMouse))
						)
						(991 ;exit 
							(theGame setCursor: 995 (HaveMouse))
						)
					)
				else
					(if (ClickedInRect 230 252 140 155 event) ;Power button
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								;(Print 8 2)
								(Print {This button turns the computer on and off.})
							)
							(995 ;hand
								(theGame setCursor: 993 (HaveMouse))
								(lite1 hide:)
								(lite2 hide:)
								(rm8 setScript: mouseComputerScript)
							)
							(else
								(event claimed: FALSE)	
							)	
						)
					)
					(if (ClickedInRect 0 41 48 180 event) ;book
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(Print 8 1)
							)
							(995 ;hand
								(Print 8 1)
							)
							(else
								(event claimed: FALSE)	
							)	
						)
					)
					(if
						(and
							(ClickedInRect 0 320 0 190 event) ;clicked anywhere else
							(== (event claimed?) FALSE)
						)
						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
								(Print 8 5)
							)
							(995 ;hand
								(Print {Press the power button to turn on the computer.})
							)
							(991 ;exit
								(curRoom newRoom: prevRoomNum)
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

(instance mouseComputerScript of Script
	(properties)	
	
	(method (init)
		(= curDir 0)
		(PrintDirs)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= badPassword 1)
				(ClearTopLine)
				(Display {PASSWORD INCORRECT!}
					p_at 73 14
					p_color 4 ;red
					p_font 7
					p_back 0
				)
				(= seconds 3)
			)
			(1
				(PrintTopLine)
				(= enteringPassword 0)
				(= badPassword 0)
			)
			(999
				(= badPassword 1)
				(ClearTopLine)
				(Display {AUTHENTICATION CANCELED}
					p_at 73 14
					p_color 14 ;yellow
					p_font 7
					p_back 0
				)
				(= state 0)
				(= seconds 3)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(if
					(and
						enteringPassword
						(not badPassword)
					)
					(if
						(and
							(< KEY_SPACE (event message?))
							(< (event message?) KEY_DELETE)
							(< pwdChar 13)
						)
						(StrAt @str pwdChar (ToUppercase (event message?)))
						(++ pwdChar)
						(StrAt @str pwdChar 0)
						(Display @str
							p_at pwdOffset 14
							p_font 7
							p_color 5 ;purple ;4
							p_back 0
						)
					)
					(if
						(and
							(== (event message?) KEY_BACK)
							(> pwdChar 0)
						)
						(-- pwdChar)
						(StrAt @str pwdChar 0) ;string must end with 0
						(GetPassword enteringPassword)
						(Display @str
							p_at pwdOffset 14
							p_font 7
							p_color 5 ;purple ;4
							p_back 0
						)
					)
					(if (== (event message?) KEY_RETURN)
						(switch enteringPassword
							(1 ;homicide
								(if (not (StrCmp @str {ICECREAM})) ;matched password
									(ClearScreen)
									(= curDir 4)
									(= enteringPassword 0)
									(PrintDirs)
								else
									(self changeState: 0)
								)
							)
							(2 ;personal
								(if (not (StrCmp @str {PISTACHIO}))
									(ClearScreen)
									(= curDir 2)
									(= enteringPassword 0)
									(PrintDirs)
								else
									(self changeState: 0)
								)
							)
							(3 ;vice
								(if (not (StrCmp @str {MIAMI}))
									(ClearScreen)
									(= curDir 7)
									(= enteringPassword 0)
									(PrintDirs)
								else
									(self changeState: 0)
								)
							)
							(else ;firearms
								(self changeState: 0)
							)
						)
						(Format @str 8 0) ;clear password
						(= pwdChar 0) 
					)
				)
			)
			(evMOUSEBUTTON
				(if
					(and
						enteringPassword
						(not badPassword)
					)
					(event claimed: TRUE)
					(self changeState: 999) ;clicking cancels password entry
				)
				(if (event claimed: FALSE)
					(if (& (event modifiers?) emRIGHT_BUTTON)
						(event claimed: TRUE)
						(switch theCursor
							(995 
								(theGame setCursor: 993 (HaveMouse))
							)
							(993 
								(theGame setCursor: 998 (HaveMouse))
							)
							(998 
								(theGame setCursor: 995 (HaveMouse))
							)
						)
					else ;handle left clicks
						(if (ClickedInRect 230 252 140 155 event) ;Power button
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									;(Print 8 1)
									(Print {This button turns the computer on and off.})
								)
								(995 ;hand
									(curRoom newRoom: prevRoomNum)
								)
								(993 ;pointer
									(theGame setCursor: 995 (HaveMouse))
									(curRoom newRoom: prevRoomNum)
								)
								(else
									(event claimed: FALSE)	
								)	
							)
						)
						(if (ClickedInRect 0 41 48 180 event) ;book
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 8 1)
								)
								(995 ;hand
									(Print 8 1)
								)
								(else
									(event claimed: FALSE)	
								)	
							)
						)
						;Screen clicks
						(if (== theCursor 993)
							(event claimed: true)
							(if enteringPassword
								;do nothing
							else
								(if (ClickedInRect 70 160 14 23 event) ;back/shutdow top line
									(switch curDir
										(0 ;shutdown
											(= curDir -1)
											(= newRoomNum prevRoomNum)
										)
										(1 ;from sierra dir
											(= curDir 0) ;to root
										)
										(2 ;from personal dir
											(= curDir 0) ;to root
										)
										(3 ;from Criminal dir
											(= curDir 0) ;to root
										)
										(4 ;from Homicide
											(= curDir 3) ;to Criminal
										)
										(7 ;from vice
											(= curDir 3) ;to Criminal
										)	
									)
									(ClearScreen)
									(PrintDirs)
								)
								(if ;clicked files
									(or
										(== curDir 1) ;Sierra
										(== curDir 2) ;Personal
										(== curDir 4) ;Homicide
										(== curDir 7) ;Vice
									)
									(= xSplit
										(switch curDir
											(1
												160
											)
											(2
												155
											)
											(4
												158
											)
											(7
												154
											)
										)
									)
									(if (ClickedInRect 65 250 24 33 event) ;line #1
										(if (< (event x?) xSplit) ;clicked left side
											(switch curDir
												(1
													(LocPrint 8 17)
												)
												(2
													(LocPrint 8 52)
													(LocPrint 8 53)
												)
												(4
													(LocPrint 8 34)
													(LocPrint 8 35)
												)
												(7
													(LocPrint 8 93)
													(LocPrint 8 94)
												)
											)
										else ;clicked right side
											(switch curDir
												(1
													(LocPrint 8 29)
												)
												(2
													(LocPrint 8 77)
													(LocPrint 8 78)
												)
												(4
													(LocPrint 8 44)
													(LocPrint 8 45)
												)
												(7
													(LocPrint 8 111)
													(LocPrint 8 112)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 34 43 event) ;line #2
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 18)
												)
												(2
													(LocPrint 8 54)
													(LocPrint 8 55)
													(LocPrint 8 56)
												)
												(4
													(LocPrint 8 36)
												)
												(7
													(LocPrint 8 95)
													(LocPrint 8 96)
												)
											)
										else
											(switch curDir
												(1
													(LocPrint 8 30)
												)
												(2
													(LocPrint 8 79)
													(LocPrint 8 80)
													(LocPrint 8 81)
												)
												(4
													(LocPrint 8 46)
													(LocPrint 8 47)
												)
												(7
													(LocPrint 8 113)
													(LocPrint 8 114)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 44 53 event) ;line #3
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 19)
												)
												(2
													(LocPrint 8 57)
													(LocPrint 8 58)
													(LocPrint 8 59)
												)
												(4
													(LocPrint 8 37)
													(LocPrint 8 38)
												)
												(7
													(LocPrint 8 97)
													(LocPrint 8 98)
												)
											)
										else
											(switch curDir
												(1
													(LocPrint 8 31)
												)
												(2
													(LocPrint 8 82)
													(LocPrint 8 83)
													(LocPrint 8 84)
													(LocPrint 8 85)
												)
												(4
													(LocPrint 8 48)
												)
												(7
													(LocPrint 8 115)
													(LocPrint 8 116)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 54 63 event) ;line #4
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 20)
												)
												(2
													(LocPrint 8 60)
													(LocPrint 8 61)
													(LocPrint 8 62)
												)
												(4
													(LocPrint 8 39)
												)
												(7
													(LocPrint 8 99)
													(LocPrint 8 100)
												)
											)
										else
											(switch curDir
												(1
													(LocPrint 8 32)
												)
												(2
													(LocPrint 8 86)
													(LocPrint 8 87)
												)
												(4
													(LocPrint 8 49)
												)
												(7
													(LocPrint 8 117)
													(LocPrint 8 118)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 64 73 event) ;line #5
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 21)
													(LocPrint 8 22)
												)
												(2
													(LocPrint 8 63)
													(LocPrint 8 64)
												)
												(4
													(LocPrint 8 40)
												)
												(7
													(LocPrint 8 101)
													(LocPrint 8 102)	
												)
											)
										else
											(switch curDir
												(1
													(LocPrint 8 33)
												)
												(2
													(LocPrint 8 88)
													(LocPrint 8 89)
												)
												(4
													(LocPrint 8 50)
												)
												(7
													(LocPrint 8 119)
													(LocPrint 8 120)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 74 83 event) ;line #6
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 23)
												)
												(2
													(LocPrint 8 65)
													(LocPrint 8 66)
												)
												(4
													(LocPrint 8 41)
													(LocPrint 8 42)
												)
												(7
													(LocPrint 8 103)
													(LocPrint 8 104)
												)
											)
										else
											(switch curDir
												(2
													(LocPrint 8 90)
													(LocPrint 8 91)
												)
												(4
													(LocPrint 8 51)
												)
												(7
													(LocPrint 8 121)
													(LocPrint 8 122)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 84 93 event) ;line #7
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 24)
													(LocPrint 8 25)
												)
												(2
													(LocPrint 8 67)
													(LocPrint 8 68)
												)
												(4
													(LocPrint 8 43)
												)
												(7
													(LocPrint 8 105)
													(LocPrint 8 106)
												)
											)
										else
											(switch curDir
												(2
													(LocPrint 8 92)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 94 103 event) ;line #8
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 26)
												)
												(2
													(LocPrint 8 69)
													(LocPrint 8 70)
													(LocPrint 8 71)
												)
												(7
													(LocPrint 8 107)
													(LocPrint 8 108)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 104 113 event) ;line #9
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 27)
												)
												(2
													(LocPrint 8 72)
													(LocPrint 8 73)
												)
												(7
													(LocPrint 8 109)
													(LocPrint 8 110)
												)
											)
										)
									)
									(if (ClickedInRect 65 250 114 123 event) ;line #10
										(if (< (event x?) xSplit)
											(switch curDir
												(1
													(LocPrint 8 28)
												)
												(2
													(LocPrint 8 74)
													(LocPrint 8 75)
													(LocPrint 8 76)
													(Bset fLearnedAboutAddiction)
												)
											)
										)
									)
								else ;clicked directories
									(if (ClickedInRect 70 153 24 33 event) ;Choice/line #1 left
										(switch curDir
											(0
												(ClearScreen)
												(= curDir 3) ;switch to Criminal dir
												(PrintDirs)
											)
											(3 ;clicked Homicide
												(GetPassword 1)
											)
										)
									)
									(if (ClickedInRect 70 153 34 43 event) ;Choice/line #2 left
										(switch curDir
											(0
												(ClearScreen)
												(= curDir 1) ;switch to sierra dir
												(PrintDirs)
											)
											(3 ;clicked Vice
												(GetPassword 3)
											)
										)
									)
									(if (ClickedInRect 70 153 44 53 event) ;Choice/line #3 left
										(switch curDir
											(0
												(GetPassword 2) ;personal
											)
											(3 ;burglary
												(GetPassword 4)
											)
										)	
									)
									(if (ClickedInRect 70 153 54 63 event) ;Choice/line #4 left
										(switch curDir
											(3 ;firearms
												(GetPassword 5)
											)
										)
									)
								)
							)
						)
						(if
							(and
								(ClickedInRect 0 320 0 190 event) ;clicked anywhere else
								(== (event claimed?) FALSE)
							)
							(event claimed: TRUE)
							(switch theCursor
								(998
									(Print 8 5)
								)
								(995 ;hand
									(Print {Press the power button to turn on the computer.})
								)
								(999 ;walk exit
									(curRoom newRoom: prevRoomNum)
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

(procedure (DirP m x y)
	(Display 8 m
		p_at x y
		p_color 9
		p_font 7
		p_back 0
	)
)

(procedure (PrintDirs)
	(PrintTopLine)
	(switch curDir
		(0 ;display root dir
			(DirP 123 73 24)
			(DirP 124 73 34)
			(DirP 125 73 44)
		)
		(1 ;display sierra files
			(DirP 126 73 24)
			(DirP 127 73 34)
			(DirP 128 73 44)
			(DirP 129 73 54)
			(DirP 130 73 64)
			(DirP 131 73 74)
			(DirP 132 73 84)
			(DirP 133 73 94)
			(DirP 134 73 104)
			(DirP 135 73 114)
			(DirP 136 160 24)
			(DirP 137 160 34)
			(DirP 138 160 44)
			(DirP 139 160 54)
			(DirP 140 160 64)
		)
		(2 ;display personel files
			(DirP 141 73 24)
			(DirP 142 73 34)
			(DirP 143 73 44)
			(DirP 144 73 54)
			(DirP 145 73 64)
			(DirP 146 73 74)
			(DirP 147 73 84)
			(DirP 148 73 94)
			(DirP 149 73 104)
			(DirP 150 73 114)
			(DirP 151 155 24)
			(DirP 152 155 34)
			(DirP 153 155 44)
			(DirP 154 155 54)
			(DirP 155 155 64)
			(DirP 156 155 74)
			(DirP 157 155 84)
		)
		(3 ;display criminal dir
			(DirP 158 73 24)
			(DirP 159 73 34)
			(DirP 160 73 44)
			(DirP 161 73 54)
		)
		(4 ;display homicide files
			(DirP 162 73 24)
			(DirP 163 73 34)
			(DirP 164 73 44)
			(DirP 165 73 54)
			(DirP 166 73 64)
			(DirP 167 73 74)
			(DirP 168 73 84)
			(DirP 169 158 24)
			(DirP 170 158 34)
			(DirP 171 158 44)
			(DirP 172 158 54)
			(DirP 173 158 64)
			(DirP 174 158 74)
		)
		(7 ;display vice files
			(DirP 175 73 24)
			(DirP 176 73 34)
			(DirP 177 73 44)
			(DirP 178 73 54)
			(DirP 179 73 64)
			(DirP 180 73 74)
			(DirP 181 73 84)
			(DirP 182 73 94)
			(DirP 183 73 104)
			(DirP 184 154 24)
			(DirP 185 154 34)
			(DirP 186 154 44)
			(DirP 187 154 54)
			(DirP 188 154 64)
			(DirP 189 154 74)
		)		
	)
)

(procedure (ClearTopLine)
	(Display 8 9
		p_at 73 14
		p_font 7
		p_color 0
		p_back 0
	)
	(Display 8 9
		p_at 73 15
		p_font 7
		p_color 0
		p_back 0
	)
;;;	(Display 8 9
;;;		p_at pwdOffset 14
;;;		p_font 7
;;;		p_color 0
;;;		p_back 0
;;;	)
;;;	(Display 8 9
;;;		p_at pwdOffset 15
;;;		p_font 7
;;;		p_color 0
;;;		p_back 0
;;;	)
)

(procedure (ClearScreen)
	(= clsIter 24)
	(while (<= clsIter 114)
		(Display 8 6
			p_at 73 clsIter
			p_font 7
			p_color 0
			p_back 0
		)
		(= clsIter (+ clsIter 10))
	)	
)

(procedure (GetPassword choice)
	(ClearTopLine)
	(= enteringPassword choice)
	(Display {Password:}
		p_at 73 14
		p_font 7
		p_color 14
		p_back 0
	)	
)

(procedure (PrintTopLine)
	(ClearTopLine)
	(Display 
		(switch curDir
			(-1
				{Session Complete.}
			)
			(0
				{ROOT [SHUTDOWN]}
			)
			(1
				{SIERRA [BACK]}
			)
			(2
				{PERSONAL [BACK]}
			)
			(3
				{CRIMINAL [BACK]}
			)
			(4
				{HOMICIDE [BACK]}
			)
			(7
				{VICE [BACK]}
			)
		)
		p_at 73 14
		p_color (switch curDir
					(-1 14) ;yellow/session complete
					(else 2) ;green
				)
		p_font 7
		p_back 0
	)	
)
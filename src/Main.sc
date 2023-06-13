;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use System)
(use IconI)
(use SysWindow)
(use Actor)

(public
	PQ 0
	
	EgoDead 2
	NotifyScript 3
	HaveMem 4
	RedrawCast 5
	cls 6
	NotClose 7
	AlreadyTook 8
	DontHave 9
	CantDo 10
	HandsOff 11
	HandsOn 12
	DontHaveGun 13
	InRoom 14
	PutInRoom 15
	Bset 16
	Bclr 17
	Btst 18
	AssignObjectToScript 19
	NormalEgo 20
	
	SolvePuzzle 22
	ClickedOnObj 23
	ClickedInRect 24
	DrawRect 25
	ClickedOnPicView 26
)

(local
	ego									;pointer to ego
	theGame								;ID of the Game instance
	curRoom								;ID of current room
	speed =  6							;number of ticks between animations
	quit								;when TRUE, quit game
	cast								;collection of actors
	regions								;set of current regions
	timers								;list of timers in the game
	sounds								;set of sounds being played
	inventory							;set of inventory items in game
	addToPics							;list of views added to the picture
	curRoomNum							;current room number
	prevRoomNum							;previous room number
	newRoomNum							;number of room to change to
	debugOn								;generic debug flag -- set from debug menu
	score								;the player's current score
	possibleScore						;highest possible score
	showStyle	=		IRISOUT			;style of picture showing
	aniInterval							;# of ticks it took to do the last animation cycle
	theCursor							;the number of the current cursor
	normalCursor =		ARROW_CURSOR	;number of normal cursor form
	waitCursor	 =		HAND_CURSOR		;cursor number of "wait" cursor
	userFont	 =		USERFONT		;font to use for Print
	smallFont	 =		4				;small font for save/restore, etc.
	lastEvent							;the last event (used by save/restore game)
	modelessDialog						;the modeless Dialog known to User and Intrface
	bigFont		=		USERFONT		;large font
	volume		=		12				;sound volume
	version		=		{x.yyy.zzz}		;pointer to 'incver' version string			
	locales								;set of current locales
	[curSaveDir 20]						;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	useSortedFeatures					;enable cast & feature sorting?
	demoScripts							;add to curRoomNum to find room demo script
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays	=		-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	;globals 62-99 are unused
;;;		global62
;;;		global63
;;;		global64
;;;		global65
;;;		global66
;;;		global67
;;;		global68
;;;		global69
;;;		global70
;;;		global71
;;;		global72
;;;		global73
;;;		global74
;;;		global75
;;;		global76
;;;		global77
;;;		global78
;;;		global79
;;;		global80
;;;		global81
;;;		global82
;;;		global83
;;;		global84
;;;		global85
;;;		global86
;;;		global87
;;;		global88
;;;		global89
;;;		global90
;;;		global91
;;;		global92
;;;		global93
;;;		global94
;;;		global95
;;;		global96
;;;		global97
		global98
		lastSysGlobal
		;globals 100 and above are for game use
	gamePhase
	debugging = 1
	global102
	gunWindageScrew
	gunElevationScrew
	global105
	isHandsOff
	dollars
	gGEgoX
	global109
	howFast
	global111
	keith
	sewerRat
	sewerLight
	sewerLight2
	theFieldKit
	global117
	global118
	global119
	global120
	global121
	global122
	global123
	global124
	gDView
	dateState
	global127
	global128
	global129
	roomCarParked
	currentCar
	global132
	workCarLocked
	global134
	workCarTrunkOpened
	personalCarLocked
	global137
	isOnDuty
	sewerCutscene
	wearingGasMask
	global141
	global142
	global143
	global144
	global145
	global146
	global147
	global148
	global149
	global150
	global151
	global152
	global153
	global154
	global155
	methaneGasTimer
	global157
	captainWarningTimer
	global159
	global160
	marieWantsCall
	global162
	talkedToKeith
	talkedToCaptain
	jailLockerOpen
	gunSightsAligned
	lloydInRehab
	global168
	global169
	global170
	global171
	global172
	global173
	global174
	showedBadgeToMotelManager
	global176
	global177
	global178
	global179
	global180
	global181
	global182
	shotAtBainsInCove ; was global183
	removedBodyFromRiver
	global185
	bainsInCoveTimer
	global187
	bainsInCoveState
	diverState
	correctScubaTank
	scubaTankOxygen
	global192
	global193
	global194
	airplaneToSteelton
	manholeIsOpen
	egoDrunk
	stewardess
	hijacker1
	hijacker2
	sittingInPlane
	wearingSeatbelt
	untiedMarie
	gunDrawn
	global205
	cSound
	bulletsInGun
	global208 ;cove shootout happened?
	unprotectedShots
	global210
	gunNotNeeded
	gunFireState ;1-gunPERMITTED, 2-gunUSELESS, 3-gunPROHIBITED (gameOver)
	global213
	global214
	numAmmoClips
	global216
	global217
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225
	global226
	global227
	global228
	global229
	global230
	global231
	global232
	global233
	global234
	global235
	global236
	muggerFleeing
	muggerArrested
	gMethaneGasTimer
	fieldKitOpen
	thisTime
	oldSysTime
	global243
	global244
	global245
	global246
	global247
	global248
	global249
	gameFlags
		global251
		global252
		global253
		global254
		global255
		global256
		global257
		global258
		global259
		global260
		global261
		global262
		global263
		global264
		global265
		global266
		global267
		global268
		global269
		global270
;**********************************************************************
; added for point & click
;**********************************************************************
    	gLayout = 1             ;Where do you want your menu
     		                    ;0 ever present on top, 1 peekaboo up top, 2 ever present on bottom
    	gGameTitle
    	yPosition = 22         ;where our peekaboo menu is at
    	movingButtons = 0    ;0 = not moving, 1 = moving down, 2 = moving up 
          
		gPreviousCursor=999         ;Used when leaving inventory screen empty handed	
		itemIcon = 900        ;cursor number of inventory item last selected ;104 ;INITIAL MONEY ICON;
    
		canTab = TRUE           ;Allow the inventory room
 		doInventory = FALSE     ;Allows easy communication between scripts, go to inventory
    
    	goToroom
	    sGauge		;game speed guage name
		vGauge		;volume guage name
		pncSpeed	;speed set with guage
		pncVolume = 16	;volume set with guage
		audVOLUME
		sGauge2		;test
		DivorceOK
		thirstTimer ;add
		tips ;add
		EasterEgg =	0 ;add
		saveVolume ;add
		programControl			;if TRUE, disable control on the next cycle
		gPseudoMouse
		gCursorNumber
		gGCursorNumber
		gGame
		gUser
		global21
		gSounds
		gEgo
		gInv
		gIconBar
		deleteCastMember					;a member of the cast needs deleting
		currentEgoView
		yPositionInventory =26 
		wearingEarProtectors
		gPEventX
		gPEventY
		gWindow
		localize
		driveDest
		driveFix
		fieldKitToggle
		gunDrawAllowed
		lista  ;from room16		
)

(procedure (EgoDead)
	;disposes all sounds, plays the death music, and gives the player a choice:
	;	Restore, Restart, Quit
	(HandsOff)
	(Wait 0)
	(Wait 150)
	(sounds eachElementDo: #dispose)
	(music number: 2 play:)
	(repeat
		(switch
			(Print &rest
				#title {Jim shakes his head and says...}
				#width 184
				#icon 555 0 0
				#button {Restore} 1
				#button { Restart_} 2
				#button { Quit_} 3
			)
			(1
				(theGame restore:)
			)
			(2
				(theGame restart:)
			)
			(3
				(= quit TRUE)
				(break)
			)
		)
	)
)


(procedure (NotifyScript script &tmp i)
	(= i (ScriptID script))
	(i notify: &rest)
	;((ScriptID script) notify: &rest)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo LargestPtr) howMuch))
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (NotClose)
	(Print 0 32)
)

(procedure (AlreadyTook)
	(Print 0 33)
)

(procedure (DontHave)
	(Print 0 35)
)

(procedure (CantDo)
	(Print 0 34)
)

;;;(procedure (HandsOff)
;;;	(cond 
;;;		((== argc 1)
;;;			(= global243 1)
;;;		)
;;;		(global243
;;;			(= global244 1)
;;;		)
;;;	)
;;;	(= isHandsOff TRUE)
;;;	(User canControl: FALSE canInput: FALSE)
;;;	(ego setMotion: 0)
;;;)
;;;
;;;(procedure (HandsOn)
;;;	(if (not global244)
;;;		(= isHandsOff FALSE)
;;;		(User canControl: TRUE canInput: TRUE)
;;;	)
;;;	(if (== argc 1)
;;;		(= global243 0)
;;;		(= global244 0)
;;;	)
;;;)
(procedure (HandsOff)
	;disable ego control
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)

)

(procedure (HandsOn)
	;enable ego control
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
)
(procedure (DontHaveGun)
	(Print 0 36)
)

(procedure (InRoom what where)
	(return
		(==
			((inventory at: what) owner?)
			(if (== argc 1) curRoomNum else where)
		)
	)
)

(procedure (PutInRoom what where)
	((inventory at: what)
		owner: (if (== argc 1) curRoomNum else where)
	)
)

;;;(procedure (Bset flagEnum)
;;;;;;	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
;;;)
;;;
;;;(procedure (Bclr flagEnum)
;;;;;;	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
;;;)
;;;
;;;(procedure (Btst flagEnum)
;;;	(return
;;;		(&
;;;			[gameFlags (/ flagEnum 16)]
;;;			(>> $8000 (mod flagEnum 16))
;;;		)
;;;	)
;;;)
;;;

(procedure (Bset flagEnum)
	;Set a boolean game flag
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bclr flagEnum)
	;Clear a boolean game flag
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
)

(procedure (Btst flagEnum)
	;Test a boolean game flag
	(return
		(if
			(&
				[gameFlags (/ flagEnum 16)]
				(>> $8000 (mod flagEnum 16))
			)
			TRUE
		else
			FALSE
		)
	)
)
(procedure (AssignObjectToScript obj theScript theState)
	(switch argc
		(2
			(obj setScript: theScript)
		)
		(3
			(obj script: theScript)
			(theScript client: obj)
			(theScript changeState: theState)
		)
	)
	(while (obj script?)
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue)
		)
		(Wait 5)
	)
)

(procedure (NormalEgo)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		setStep: 3 2
		ignoreActors: 0
	)
)

(procedure (SolvePuzzle pValue pFlag &tmp [str 200])
	(if
		(and
			(== argc 2)
			(Btst pFlag)
		)
		(return)
	)
	(theGame changeScore: pValue)
	
	(if (Btst fGotPoints)
		(Bclr fGotPoints)
	else
		(music number: 6 play:)
;;;		(Print (Format @str 0 0 score possibleScore)) ;add
	)
	(if (== argc 2)
		(Bset pFlag)
	)
)

(procedure (ClickedOnObj obj eventX eventY)
	(if 
		(and
			(< eventX (obj nsRight?))
			(> eventX (obj nsLeft?))
			(< eventY (obj nsBottom?))
			(> eventY (obj nsTop?))
		)
		(return)
	)
)

(procedure (ClickedInRect l r t b event)
	(if
		(and
			(> (event x?) l) ;left edge
			(< (event x?) r) ;right edge
			(> (event y?) t) ;top edge
			(< (event y?) b) ;bottom edge
		)
		
		(return TRUE)
	else
		(return FALSE)
		
	)
)

(procedure (DrawRect param1 param2 param3 param4 param5 &tmp v l c col r t b)
	(if (< argc 3) ;one or two params obj & optional color
		(= v (param1 view?))
		(= l (param1 loop?))
		(= c (param1 cel?))
		(if (> argc 1) ;if sent optional color
			(= col param2)
		else
			(= col 4) ;default color red (4)
		)
		(Graph
			GDrawLine
			(param1 y?)
			(+ (param1 x?) (/ (CelWide v l c) 2))
			(param1 y?)
			(- (param1 x?) (/ (CelWide v l c) 2))
			col
		)
		(Graph
			GDrawLine
			(param1 y?)
			(+ (param1 x?) (/ (CelWide v l c) 2))
			(- (param1 y?) (CelHigh v l c))
			(+ (param1 x?) (/ (CelWide v l c) 2))
			col
		)
		(Graph
			GDrawLine
			(- (param1 y?) (CelHigh v l c))
			(+ (param1 x?) (/ (CelWide v l c) 2))
			(- (param1 y?) (CelHigh v l c))
			(- (param1 x?) (/ (CelWide v l c) 2))
			col
		)
		(Graph
			GDrawLine
			(- (param1 y?) (CelHigh v l c))
			(- (param1 x?) (/ (CelWide v l c) 2))
			(param1 y?)
			(- (param1 x?) (/ (CelWide v l c) 2))
			col
		)
	else ;sent 4 or 5 paramsL l r t b and optional color
		(= l param1)
		(= r param2)
		(= t param3)
		(= b param4)
		(if (> argc 4) ;if sent optional color
			(= col param5)
		else
			(= col 4) ;default color red (4)
		)
		(Graph GDrawLine t l t r col) ;top-left to top right
		(Graph GDrawLine t r  b r col) ;top-right to bottom right...
		(Graph GDrawLine b r b l col) 
		(Graph GDrawLine b l t l col) 
	)
)

(procedure (ClickedOnPicView obj eventX eventY)
	(if 
		(and
			(< eventX (+ (obj x?) (/ (CelWide (obj view?)(obj loop?)(obj cel?))2)))
			(> eventX (- (obj x?) (/ (CelWide (obj view?)(obj loop?)(obj cel?))2)))
			(< eventY (obj y?))
			(> eventY (- (obj y?) (CelHigh (obj view?)(obj loop?)(obj cel?))))
		)
		(return)
	)
)

(instance diverClock of TimeOut)

(instance continuousMusic of Sound)

(instance music of Sound
	(properties
		priority 10
	)
)

(instance statusCode of Code
	(method (doit strg)
		(Format strg 0 0 score possibleScore)
	)
)

(instance egoObj of Ego
	(properties
		name {ego}
	)
)

;;;(instance icon4 of IconI
;;;	(properties
;;;		view 950
;;;		loop 4
;;;		cel 0
;;;		cursor 999
;;;		message 7
;;;		signal $0041
;;;		maskView 900
;;;		maskLoop 10
;;;		maskCel 3
;;;		noun 4
;;;		helpVerb 8
;;;	)
;;;	
;;;	(method (select param1 &tmp newEvent temp1 gIconBarCurInvIcon temp3 temp4)
;;;		(return
;;;			(cond 
;;;				((& signal $0004) 0)
;;;				((and argc param1 (& signal notUpd))
;;;					(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
;;;						(= temp3
;;;							(+
;;;								(/
;;;									(-
;;;										(- nsRight nsLeft)
;;;										(CelWide
;;;											(gIconBarCurInvIcon view?)
;;;											(+ (gIconBarCurInvIcon loop?) 1)
;;;											(gIconBarCurInvIcon cel?)
;;;										)
;;;									)
;;;									2
;;;								)
;;;								nsLeft
;;;							)
;;;						)
;;;						(= temp4
;;;							(+
;;;								(gIconBar y?)
;;;								(/
;;;									(-
;;;										(- nsBottom nsTop)
;;;										(CelHigh
;;;											(gIconBarCurInvIcon view?)
;;;											(+ (gIconBarCurInvIcon loop?) 1)
;;;											(gIconBarCurInvIcon cel?)
;;;										)
;;;									)
;;;									2
;;;								)
;;;								nsTop
;;;							)
;;;						)
;;;					)
;;;					(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
;;;					(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
;;;						(DrawCel
;;;							(gIconBarCurInvIcon view?)
;;;							(+ 1 (gIconBarCurInvIcon loop?))
;;;							(gIconBarCurInvIcon cel?)
;;;							temp3
;;;							temp4
;;;							-1
;;;						)
;;;					)
;;;;;;					(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
;;;;;;					(while (!= ((= newEvent (Event new:)) type?) 2)
;;;;;;						(newEvent localize:)
;;;;;;						(cond 
;;;;;;							((self onMe: newEvent)
;;;;;;								(if (not temp1)
;;;;;;									(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
;;;;;;									(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
;;;;;;										(DrawCel
;;;;;;											(gIconBarCurInvIcon view?)
;;;;;;											(+ 1 (gIconBarCurInvIcon loop?))
;;;;;;											(gIconBarCurInvIcon cel?)
;;;;;;											temp3
;;;;;;											temp4
;;;;;;											-1
;;;;;;										)
;;;;;;									)
;;;;;;									(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
;;;;;;								)
;;;;;;							)
;;;;;;							(temp1
;;;;;;								(DrawCel view loop (= temp1 0) nsLeft nsTop -1)
;;;;;;								(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
;;;;;;									(DrawCel
;;;;;;										(gIconBarCurInvIcon view?)
;;;;;;										(+ 1 (gIconBarCurInvIcon loop?))
;;;;;;										(gIconBarCurInvIcon cel?)
;;;;;;										temp3
;;;;;;										temp4
;;;;;;										-1
;;;;;;									)
;;;;;;								)
;;;;;;								(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
;;;;;;							)
;;;;;;						)
;;;;;;						(newEvent dispose:)
;;;;;;					)
;;;;;;					(newEvent dispose:)
;;;;;;					(if (== temp1 1)
;;;;;;						(DrawCel view loop 0 nsLeft nsTop -1)
;;;;;;						(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
;;;;;;							(DrawCel
;;;;;;								(gIconBarCurInvIcon view?)
;;;;;;								(+ 1 (gIconBarCurInvIcon loop?))
;;;;;;								(gIconBarCurInvIcon cel?)
;;;;;;								temp3
;;;;;;								temp4
;;;;;;								-1
;;;;;;							)
;;;;;;						)
;;;;;;						(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
;;;;;;					)
;;;;;;	
;;;				temp1
;;;				)
;;;				(else 1)
;;;			)
;;;		)
;;;	)
;;;)


(instance PQ of Game
	(method (init &tmp [temp0 21])
				(Load VIEW 950)
		(= systemWindow (SysWindow new:))
		(super init:)
		;(= debugging FALSE)
		(= debugging TRUE)	;added to enable debug features
		
		;"kiss angel death" now toggles debugging
		(= ego egoObj)
		(User alterEgo: ego blocks: 0 y: 155)

;;;		(TheMenuBar init:)
		

		(StatusLine code: statusCode)
		(TheMenuBar init:)		
		
		
;;;		(StatusLine code: statusCode)
		(= possibleScore 300)
		(= captainWarningTimer 700)
		(= gunWindageScrew
			(switch (Random 1 2)
				(1 (Random 8 18))
				(2 (- 0 (Random 8 18)))
			)
		)
		(= gunElevationScrew
			(switch (Random 1 2)
				(1 (Random 6 14))
				(2 (- 0 (Random 6 14)))
			)
		)
		(= dollars 36)
		(= [numAmmoClips 1] 7)
		(= [numAmmoClips 2] 7)
		(= workCarLocked 1)
		(= global134 1)
		(= personalCarLocked 1)
		(= currentCar carPersonal)
		(= methaneGasTimer -1)
		(= correctScubaTank (Random 1 3))
		(Bset fEgoDeskLocked) ;flag was fEgoDeskUnlocked. Renamed for clarity - DL
		(= version {1.002.011})
		(Inventory
			add:
				hand_gun
				extra_ammo_clips
				key_ring
				unmarked_car_keys
				money_clip
				thank_you_letter
				death_threat
				wallet
				handcuffs
				wire_clippers
				field_kit
				potted_plant
				new_mug_shot
				hit_list
				makeshift_knife
				ear_protectors
				plane_ticket
				plaster_cast
				lost_badge
				thumbprint
				bullets
				empty_holster
				fingerprint
				old_mug_shot
				envelope_corner
				envelope
				jail_clothes
				motel_key
				vial_of_blood
				lipstick
				walkie_talkie
				jailer_s_revolver
				gas_mask
				bomb_instructions
				car_registration
				Colby_s_business_card
				note_from_Marie_s_door
				your_LPD_business_card
				warrant
		)

		(ego get: iMoneyClip)
		;(ego get: 10 get: 2 get: 4 get: 5 get: 6 get: 7 get: 9 get: 11 get: 12 get: 13 get: 14 get: 15 get: 16 get: 17 get: 18 get: 19 get: 20 get: 21 get: 22 get:23 get:24 get:25 get:26 get: 27) ;maletin
		;(ego get: 0  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37) ;maletin
		(ego get: 37 0 1 2 3 4 10 8 5 7)
		

		(HandsOn)
		(= showStyle HSHUTTER)
		(DoSound ChangeVolume 15)
		((= cSound continuousMusic)
			owner: self
			number: 6
			init:
		)
		(music owner: self number: 6 init:)
		(if (GameIsRestarting)
			(Bset fGameIsRestarting)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(self newRoom: 99) ;99
		else
			(TheMenuBar draw:) ;add
			(StatusLine enable:) ;add
			(self newRoom: 99) ;99
		)
	
	)
	
	(method (doit &tmp newEvent)
		(TheMenuBar draw:)
		(StatusLine enable:)
		
		
		
		(super doit:)
		(diverClock doit:)
		(if (!= oldSysTime (= thisTime (GetTime TRUE)))
			(= oldSysTime thisTime)
			(if (> captainWarningTimer 1)
				(-- captainWarningTimer)
			)
			(if (> global159 1)
				(-- global159)
			)
		)
		(if (> global157 1)
			(-- global157)
		)
		(if (> global170 1)
			(-- global170)
		)
		(if (> global171 1)
			(-- global171)
		)
		(if (> bainsInCoveTimer 0)
			(-- bainsInCoveTimer)
		)
		(if (== diverState 1)
			(diverClock set: 600)
			(= diverState 2)
		)
		(if (and (== diverState 2) (< (diverClock timeLeft?) 1))
			(= diverState 3)
		)
		(if (== diverState 10)
			(diverClock set: 500)
			(= diverState 11)
		)
		(if (and (== diverState 11) (< (diverClock timeLeft?) 1))
			(= removedBodyFromRiver TRUE)
			(= diverState 12)
		)
		(if (Btst fPnCVolumeFlag)
			(= volume pncVolume)
			(DoSound ChangeVolume volume)
		)
		(if (Btst fPnCSaveFlag)
			(Bclr fPnCSaveFlag)
			(theGame save:)	
		)
		(if fieldKitToggle
			(= fieldKitToggle 0)
			(= newEvent (Event new:))
			(newEvent
				type: evKEYBOARD
				message: {open briefcase}
				modifiers: 999
				claimed: 0
			)
			(User handleEvent: newEvent)
            (newEvent dispose:)	
		)
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(if (DoSound SoundOn)
			(SetMenu soundI p_value FALSE p_text {Turn Off})
		else
			(SetMenu p_value TRUE p_text {Turn On})
		)
		(super replay:)
	)
	
	(method (startRoom roomNum &tmp temp0 avd evt [temp3 50])
		(while ((= evt (Event new:)) type?)
			(evt dispose:)
		)
		(evt dispose:)
		(DisposeScript 301)
		(DisposeScript 976)
		(if (and (!= roomNum carWork) (!= roomNum carPersonal))
			(= avd Avoider)
		else
			(DisposeScript AVOIDER)
		)
		(if
			(and
				debugging
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(Print 0 4 #button {Debug} 1)
			)
			(SetDebug)
		)
		(= gunNotNeeded TRUE)
		(= gunFireState gunUSELESS)
		(if
			(and
				(!= roomNum 1)
				(!= roomNum 13)
				(!= roomNum 14)
				(!= roomNum 22)
				(!= roomNum 25)
				(!= roomNum 225)
				(!= roomNum 27)
				(!= roomNum 29)
				(!= roomNum 31)
				(!= roomNum 33)
				(!= roomNum 61)
				(!= roomNum 67)
			)
			(SetMenu carI p_state FALSE)
		else
			(SetMenu carI p_state TRUE)
		)
		(= global132 0)
		(if (and (< bainsInCoveTimer 250) bainsInCoveState)
			(= bainsInCoveState 2)
		)
		(super startRoom: roomNum)
		(if
			(and
				gunDrawn
				(!= (ego view?) 7)
				(!= (ego view?) 306)
				(!= (ego view?) 6)
			)
			(= gunDrawn FALSE)
		)
		(if global214
			(= global214 0)
			(HandsOn 1)
			(switch (ego view?)
				(4 (ego view: 0))
				(5 (ego view: 1))
			)
		)
		(if (and (!= roomNum carWork) (!= roomNum carPersonal))
			(curRoom setLocales: regGun)
		)
		(if debugging
			(curRoom setLocales: DEBUG)
		)
		
		(if
			(or
				(== curRoomNum 1)
				(== curRoomNum 2)
				(== curRoomNum 3)
				(== curRoomNum 4)
				(== curRoomNum 5)
				(== curRoomNum 6) 
				(== curRoomNum 7)
				(== curRoomNum 8)
				(== curRoomNum 10)
				;(== curRoomNum 12) no menu in phone room
				(== curRoomNum 13)
				(== curRoomNum 14)
				(== curRoomNum 15)
				(== curRoomNum 16)
				(== curRoomNum 17)
				(== curRoomNum 18)
				(== curRoomNum 19)				
				(== curRoomNum 20)								
				(== curRoomNum 22)
				(== curRoomNum 23)
				(== curRoomNum 25)
				(== curRoomNum 26)
				(== curRoomNum 27)
				(== curRoomNum 28)
				(== curRoomNum 29)
				(== curRoomNum 30)
				(== curRoomNum 31)
				(== curRoomNum 32)								
				(== curRoomNum 33)
				(== curRoomNum 35)
				(== curRoomNum 40)
				(== curRoomNum 41)
				(== curRoomNum 42)
				(== curRoomNum 43)				
				(== curRoomNum 44)
				(== curRoomNum 60)
				(== curRoomNum 61)
				(== curRoomNum 62)
				(== curRoomNum 63)
				(== curRoomNum 64)
				(== curRoomNum 65)		
				(== curRoomNum 66)							
				(== curRoomNum 67)
				(== curRoomNum 68)
				(== curRoomNum 78)
				(== curRoomNum 79)
				(== curRoomNum 80)
				(== curRoomNum 81)
				(== curRoomNum 82)
				(== curRoomNum 91)												
				(== curRoomNum 92)
				(== curRoomNum 101)
				(== curRoomNum 104)
				(== curRoomNum 105)
				(== curRoomNum 120)
				(== curRoomNum 121)								
				(== curRoomNum 122)
				(== curRoomNum 123)
				(== curRoomNum 124)				
				(== curRoomNum 125)
				(== curRoomNum 126)
				(== curRoomNum 127)
				(== curRoomNum 128)
				(== curRoomNum 129)
				(== curRoomNum 130)
				(== curRoomNum 131)
				(== curRoomNum 132)								
				(== curRoomNum 133)
				(== curRoomNum 225)				
			)
			(curRoom setRegions: 950)
		)
		(if ;prevent drawing gun from PnCMenu
			(or
				(== curRoomNum 7) ;file cabinet
				(== curRoomNum 13) ;unmarked car
				(== curRoomNum 33) ;personal car
				(== curRoomNum 166) ;driving map
			)
			(= gunDrawAllowed 0) ;gun not allowed
		else
			(= gunDrawAllowed 1)
		)					
		(Load SOUND 6)
	)
	
	(method (handleEvent event &tmp index [temp1 53])
		(super handleEvent: event)
		(if (event claimed?) (return))
		
		(if
			(and
				(not (super handleEvent: event))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)

		
		(switch (event type?)
			(saidEvent
				(cond 
					;((and (Said 'kiss/angel>') (Said '/death'))
					((Said 'kiss/death<angel')
						(event claimed: TRUE)
						(if (^= debugging TRUE)
							(curRoom setLocales: DEBUG)
						)
					)
					((Said 'frisk,(look<in,through)/billfold')
						(if (not (ego has: iWallet))
							(DontHave)
						else
							(Print 800 50)
							(SolvePuzzle 2 fFirstFindDivingCard)
							(Bset fFoundDiverCard)
						)
					)
					((Said 'open/billfold')
						(if (ego has: iWallet)
							((inventory at: iWallet) showSelf:)
						else
							(DontHave)
						)
					)
					(
						(or
							(Said 'kiss,fuck,crap,cunt,cocksucker,leak')
							(Said '/fuck,crap,cunt,cocksucker,leak')
							(Said 'eat/crap,and,die')
						)
						(Print 800 (Random 0 7))
					)
					((Said 'read/instruction,newspaper')
						(if (ego has: iBombInstructions)
							(Print 800 53 #font smallFont)
						else
							(DontHave)
						)
					)
					((Said 'look,compare,check/handwriting,note,handwriting[<hand]')
						(if (ego has: iMarieDoorNote)
							(SolvePuzzle 3 fCheckMarieHandwriting)
							(Print 0 5
								#at -1 15
								#icon 136 0 0
							)
						else
							(DontHave)
						)
					)
					((Said 'holler')
						(Print 0 6)
					)
					((Said 'chat>')
						(cond 
							((Said '/friend')
								(cond 
									((== curRoomNum carWork)
										(Print 800 (Random 10 15))
									)
									(
										(or
											(not (cast contains: keith))
											(not (keith inRect: -10 -10 340 240))
										)
										(Print 0 7)
									)
									((> (ego distanceTo: keith) 30)
										(Print 0 8)
									)
									(else
										(Print 800 (Random 10 15))
									)
								)
							)
							((Said '/noword')
								(Print 0 9)
							)
							(else
								(event claimed: TRUE)
								(Print 0 10)
							)
						)
					)
					((or (Said '[say]/hello') (Said 'smile,wave'))
						(Print 0 11)
					)
					((Said '[say]/bye')
						(Print 0 12)
					)
					((Said 'open/door')
						(CantDo)
					)
					(
						(or
							(Said 'use,remove/powder,brush')
							(Said 'deposit,apply,use/powder,dust')
							(Said 'dust,powder[/anyword]')
							(Said 'get,remove,hoist/fingerprint,print[<finger]')
							(Said 'open,use,look/briefcase')
						)
						(if (== gamePhase 14)
							(Print 0 13)
						else
							(Print 0 14)
						)
					)
					((Said 'get/anyword')
						(event claimed: FALSE)
						(if
							(and
								(= index (inventory saidMe: event))
								(== (index owner?) ego)
							)
							(AlreadyTook)
						else
							(event claimed: TRUE)
							(Print 0 15)
						)
					)
					((Said 'use/anyword')
						(Print 0 16)
					)
					((Said 'deposit,deposit/anyword')
						(Print 0 14)
					)
					((Said 'ask>')
						(cond 
							((Said '/noword')
								(Print 0 17)
							)
							((Said '//noword')
								(Print 0 18)
							)
						)
					)
					((Said 'eat/9mm')
						(Print 0 19)
					)
					((Said 'eat')
						(Print 0 20)
					)
					((Said 'turn/card')
						(cond 
							((not (ego has: iLPDBusinessCard))
								(DontHave)
							)
							((your_LPD_business_card cel?)
								(your_LPD_business_card cel: 0 showSelf:)
							)
							(else
								(your_LPD_business_card cel: 1 showSelf:)
							)
						)
					)
					((or (Said 'thank') (Said '/thanks'))
						(Print 0 21)
					)
					((Said '/oop,oop')
						(Print 0 22)
					)
					((Said 'affirmative,n')
						(Print 0 23)
					)
					((Said 'sat')
						(Print 0 10)
					)
					((Said 'beat,kill,fire,beat')
						(Print 0 24)
					)
					((Said 'cigarette')
						(Print 800 51)
					)
					((Said 'gave[/anyword]')
						(Print 0 14)
					)
					((Said 'frisk>')
						(if (Said '/noword')
							(Print 0 25)
						else
							(event claimed: TRUE)
							(Print 0 26)
						)
					)
					((Said 'look,read>')
						(cond 
							((or (Said '/pocket<coat') (Said '/coat<pocket'))
								(event claimed: FALSE)
							)
							((Said '/pocket')
								(Print 0 27)
							)
							((Said '/certificate')
								(if (Btst fFoundDiverCard)
									(Print 800 49
										#icon 164 0 0
									)
								else
									(Print 800 52)
								)
							)
							((or (Said '<back/card') (Said '/back/card'))
								(if (ego has: iLPDBusinessCard)
									(your_LPD_business_card cel: 1 showSelf:)
								else
									(DontHave)
								)
							)
							((or (Said '<front/card') (Said '/front/card'))
								(if (ego has: iLPDBusinessCard)
									(your_LPD_business_card cel: 0 showSelf:)
								else
									(DontHave)
								)
							)
							;adding the /card check below allows the turn/card state to work properly.
							;issue: 'look/back/card' incorrectly shows the front of the card, while 'look/card/back' shows the locker combination. 
							;In retail, 'look/back/card' is also not quite working. It incorrectly gives the 'spinning your wheels.' look responses.
							((Said '/card') 
								(if (ego has: iLPDBusinessCard)
									(your_LPD_business_card showSelf:)
								else
									(DontHave)
								)
							)
							((Said '/friend')
								(if
									(or
										(and
											(cast contains: keith)
											(< (ego distanceTo: keith) 150)
										)
										(== curRoomNum 13)
									)
									(Print 800 (Random 20 24))
								else
									(Print 0 7)
								)
							)
							(
								(or
									(Said '/bains,john,dooley,lloyd,gelepsi,captain,hall,james,pierson')
									(Said '/simpson,bob,adams,cole,jerome,ken,ken,saxton,luis')
									(Said '/roberts,calvin,calvin,willis,jerk,diver,chuck,colby,miller')
								)
								(Print 0 28)
							)
							((Said '/kim,holt,gomez,cheeks,cheeks,holt')
								(Print 0 29)
							)
							((Said '/boob')
								(if
									(or
										(== curRoomNum 3)
										(== curRoomNum 6)
										(== curRoomNum 61)
										(== curRoomNum 30)
										(== curRoomNum 40)
									)
									(Print 0 30)
								else
									(Print 0 31)
								)
							)
							((Said '/clock,wrist,time')
								(Print 800 9)
							)
							((= index (inventory saidMe: event))
								(if (ego has: (inventory indexOf: index))
									(index showSelf:)
								else
									(DontHave)
								)
							)
							(else
								(event claimed: TRUE)
								(Print 800 (Random 30 32))
							)
						)
					)
				)
			)
		)
	)
	
	(method (wordFail word &tmp [str 40])
		(Printf 0 1 word)
	)
	
	(method (syntaxFail)
		(Print 0 2
			#icon 555 1 0
		)
	)
	
	(method (pragmaFail &tmp [str 100])
		(Print 0 3)
	)
)

(class Iitem of InvItem
	(method (showSelf)
		(Print 899 (- view 100)
			#title name
			#icon view 0 cel)
	)
)

(instance hand_gun of Iitem
	(properties
		said '/9mm[<hand]'
		owner 5
		view 100
		name "hand gun"
	)
)

(instance extra_ammo_clips of Iitem
	(properties
		said '/ammo,(clip[<ammo])'
		owner 5
		view 101
		name "extra ammo clips"
	)
	
	(method (ownedBy param1)
		(switch bulletsInGun
			(0
				(= cel
					(+ 2 (> [numAmmoClips 1] 0) (> [numAmmoClips 2] 0))
				)
			)
			(1
				(= cel (> [numAmmoClips 2] 0))
			)
			(else 
				(= cel (> [numAmmoClips 1] 0))
			)
		)
		(super ownedBy: param1)
	)
)

(instance key_ring of Iitem
	(properties
		said '/ring<key'
		view 102
		name "key ring"
	)
	
	(method (saidMe event)
		(return
			(if (and (ego has: iUnmarkedCarKeys) (Said '/key'))
				(event claimed: FALSE)
				(return FALSE)
			else
				(return (if (Said '/key') else (Said said)))
			)
		)
	)
)

(instance unmarked_car_keys of Iitem
	(properties
		said '/key[<auto]'
		owner 4
		view 103
		name "unmarked car keys"
	)
)

(instance money_clip of InvItem
	(properties
		said '/coat,(pocket<coat),cash,(clip[<cash])'
		view 104
		name "money clip"
	)
	
	(method (showSelf &tmp [str 40])
		(Print
			(Format @str 0 37 dollars)
			#title name
			#icon view 0 (if (== dollars 0) 1 else 0)
		)
	)
)

(instance thank_you_letter of Iitem
	(properties
		said '/letter[<ya<thank]'
		owner 12
		view 105
		name "thank you letter"
	)
)

(instance death_threat of Iitem
	(properties
		said '/threat,note<death'
		owner 28
		view 106
		name "death threat"
	)
)

(instance wallet of Iitem
	(properties
		said '/billfold,badge,(card<badge)'
		owner 12
		view 107
	)
	
	(method (saidMe event)
		(return
			(if (and (ego has: iLostBadge) (Said '/badge'))
				(event claimed: FALSE)
				(return FALSE)
			else
				(return (Said said))
			)
		)
	)
)

(instance handcuffs of Iitem
	(properties
		said '/arrest'
		owner 5
		view 108
	)
)

(instance wire_clippers of Iitem
	(properties
		said '/clipper,clipper'
		view 109
		name "wire clippers"
	)
)

(instance field_kit of Iitem
	(properties
		said '/briefcase[<field]'
		owner 2
		view 110
		name "field kit"
	)
)

(instance potted_plant of Iitem
	(properties
		said '/flower,rose,plant,bouquet'
		owner 15
		view 111
		name "potted plant"
	)
	
	(method (showSelf &tmp [str 40])
		(Print
			(Format @str 0 38
				(switch cel
					(0 {potted plant})
					(1 {single long-stemmed rose})
					(2 {bouquet})
				)
			)
			#title name
			#icon view loop cel
		)
	)
	
	(method (ownedBy whom)
		(switch cel
			(0 (= name {potted plant}))
			(1 (= name {rose}))
			(2 (= name {bouquet}))
		)
		(super ownedBy: whom)
	)
)

(instance new_mug_shot of Iitem
	(properties
		said '/mugshot,(shot<mug)'
		owner 23
		view 112
		name "new mug shot"
	)
	
	(method (saidMe event)
		(return
			(cond 
				((!= owner ego)
					(return FALSE)
				)
				((or (Said '/mugshot<old') (Said '/shot<mug<old'))
					(event claimed: FALSE)
					(return FALSE)
				)
				(else
					(return (Said said))
				)
			)
		)
	)
)

(instance hit_list of Iitem
	(properties
		said '/list[<body,beat]'
		owner 32
		view 113
		name "hit list"
	)
)

(instance makeshift_knife of Iitem
	(properties
		said '/knife'
		owner 64
		view 114
		name "makeshift knife"
	)
)

(instance ear_protectors of Iitem
	(properties
		said '/ep[<ear]'
		owner 10
		view 115
		name "ear protectors"
	)
)

(instance plane_ticket of InvItem
	(properties
		said '/ticket[<airplane]'
		view 116
		name "plane ticket"
	)
	
	(method (showSelf &tmp [str 40])
		(Print
			(Format @str 0 39
				(if (== airplaneToSteelton TRUE) {Steelton} else {Houston})
			)
			#title name
			#icon view 0 0
		)
	)
)

(instance plaster_cast of Iitem
	(properties
		said '/cast,(print<feet)'
		view 117
		name "plaster cast"
	)
)

(instance lost_badge of Iitem
	(properties
		said '/badge'
		owner 63
		view 118
		name "lost badge"
	)
)

(instance thumbprint of Iitem
	(properties
		said '/thumb,(print<thumb)'
		view 119
	)
)

(instance bullets of Iitem
	(properties
		said '/bullet'
		owner 68
		view 120
	)
)

(instance empty_holster of Iitem
	(properties
		said '/gunbelt'
		owner 68
		view 121
		name "empty holster"
	)
)

(instance fingerprint of Iitem
	(properties
		said '/(print<finger),fingerprint'
		view 122
	)
)

(instance old_mug_shot of Iitem
	(properties
		said '/mugshot,(shot<mug)'
		owner 7
		view 123
		name "old mug shot"
	)
)

(instance envelope_corner of Iitem
	(properties
		said '/corner[<envelope]'
		owner 28
		view 124
		name "envelope corner"
	)
)

(instance envelope of Iitem
	(properties
		said '/envelope'
		owner 26
		view 125
	)
)

(instance jail_clothes of Iitem
	(properties
		said '/cloth'
		owner 62
		view 126
		name "jail clothes"
	)
)

(instance motel_key of Iitem
	(properties
		said '/key<inn'
		owner 25
		view 127
		name "motel key"
	)
)

(instance vial_of_blood of Iitem
	(properties
		said '/vial,blood,sample[<blood]'
		owner 26
		view 128
		name "vial of blood"
	)
)

(instance lipstick of Iitem
	(properties
		said '/television,(baton<lip),lipstick'
		owner 26
		view 129
	)
)

(instance walkie_talkie of Iitem
	(properties
		said '/(talkie[<walkie]),extender'
		owner 101
		view 130
		name "walkie talkie"
	)
)

(instance jailer_s_revolver of Iitem
	(properties
		said '/revolver,(9mm<jailer)'
		owner 19
		view 131
		name "jailer's revolver"
	)
)

(instance gas_mask of Iitem
	(properties
		said '/mask[<gas]'
		owner 126
		view 132
		name "gas mask"
	)
)

(instance bomb_instructions of Iitem
	(properties
		said '/instruction[<bomb]'
		view 133
		name "bomb instructions"
	)
)

(instance car_registration of Iitem
	(properties
		said '/registration'
		view 134
		name "car registration"
	)
)

(instance Colby_s_business_card of Iitem
	(properties
		said '/(card<business<colby),(card<colby)'
		owner 26
		view 135
		name "Colby's business card"
	)
	
	(method (saidMe event)
		(return
			(if (and (ego has: iLPDBusinessCard) (Said '/card[<noword]'))
				(event claimed: FALSE)
				(return FALSE)
			else
				(return (if (Said '/card[<noword]') else (Said said)))
			)
		)
	)
)

(instance note_from_Marie_s_door of Iitem
	(properties
		said '/note[<door]'
		owner 31
		view 136
		name "note from Marie's door"
	)
)

(instance your_LPD_business_card of Iitem
	(properties
		said '/card[<business]'
		owner 33
		view 137
		name "your LPD business card"
	)
)
(instance warrant of Iitem
	(properties
		said '/warrant'
		view 138
		name "warrant"
	)
)

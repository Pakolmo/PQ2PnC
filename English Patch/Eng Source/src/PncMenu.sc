;;; Sierra Script 1.0 - (do not remove this comment)
(script# 950)
(include game.sh) (include menu.sh)
(use Main)
(use Game)
(use Gauge)
(use System)
(use Actor)
(use Intrface)
(use user)
(use Motion)
(use Invent)
(use Gun)

(public
	PnCMenu 0
)

(local
	[txtstring 50]
	yIconStep =  4
	howLong =  50
	gShowMenu
	doMenuTimer
	menuTime
	pickedLoad ;added for load save merge
	newEvent
	tempCur
)
(instance PnCMenu of Region
	(properties)
	
	(method (init)
		(super init:)
		(self setScript: PnCMenuScript)
		(switch gLayout
			(0
;;;				(= yPosition 22)
;;;				(= yPositionInventory 18)
;;;				(walkIcon setPri: 15 y: yPosition init:)
;;;				(lookIcon setPri: 15 y: yPosition init:)
;;;				(handIcon setPri: 15 y: yPosition init:)
;;;				(talkIcon setPri: 15 y: yPosition init:)
;;;				(gunIcon setPri: 15 y: yPosition init:)
;;;				;(background setPri: 15 y: yPosition init:)
;;;				;;(invIcon setPri: 15 y: yPosition init:)
;;;				;;(blockIcon setPri: 14 y: yPositionInventory init:) ;was setPri: 14
;;;				(levelsIcon setPri: 15 y: yPosition init:)
;;;				;(restartIcon setPri: 15 y: yPosition init:)
;;;				;(loadIcon setPri: 15 y: yPosition init:)
;;;				(saveIcon setPri: 15 y: yPosition init:)
;;;				(quitIcon setPri: 15 y: yPosition init:)
;;;				(if (!= itemIcon 900) ;nothing
;;;					(selectedItem setPri: 15 y: yPositionInventory cel: itemIcon init:)
;;;				else
;;;					(selectedItem setPri: 15 y: yPositionInventory cel: 0 init:)
;;;				)
			)
			(2
;;;				(= yPosition 189)
;;;				(= yPositionInventory 18)
;;;				(walkIcon setPri: 15 y: yPosition init:)
;;;				(lookIcon setPri: 15 y: yPosition init:)
;;;				(handIcon setPri: 15 y: yPosition init:)
;;;				(talkIcon setPri: 15 y: yPosition init:)				
;;;				(gunIcon setPri: 15 y: yPosition init:)
;;;				;(background setPri: 15 y: yPosition init:)
;;;				(invIcon setPri: 15 y: yPosition init:)
;;;				(blockIcon setPri: 14 y: yPositionInventory init:) ;was setPri: 14
;;;				(levelsIcon setPri: 15 y: yPosition init:)
;;;				;(restartIcon setPri: 15 y: yPosition init:)
;;;				;(loadIcon setPri: 15 y: yPosition init:)
;;;				(saveIcon setPri: 15 y: yPosition init:)
;;;				(quitIcon setPri: 15 y: yPosition init:)
;;;				(if (!= itemIcon 900) ;nothing
;;;					(selectedItem setPri: 15 y: yPositionInventory cel: itemIcon init:)
;;;				else
;;;					(selectedItem setPri: 15 y: yPositionInventory cel: 0 init:)
;;;				)
			)
			(else 
				(walkIcon setPri: 15 y: yPosition init:)
				(lookIcon setPri: 15 y: yPosition init:)
				(handIcon setPri: 15 y: yPosition init:)
				(talkIcon setPri: 15 y: yPosition init:)
				(gunIcon setPri: 15 y: yPosition init:)
				;(background setPri: 15 y: yPosition init:)
				(invIcon setPri: 15 y: yPosition init:)
 				(blockIcon setPri: 14 y: yPosition init:) ;was setPri: 14
				(levelsIcon setPri: 15 y: yPosition init:)
				;(restartIcon setPri: 15 y: yPosition init:)
				;(loadIcon setPri: 15 y: yPosition init:)
				(saveIcon setPri: 15 y: yPosition init:)
				(quitIcon setPri: 15 y: yPosition init:)
				(if (!= itemIcon 900) ;nothing
;;;					(blockIcon
;;;						setPri: 14
;;;						y: yPosition
;;;						cel: itemIcon
;;;						init:
;;;						setScript: showButtons
;;;					)
					(selectedItem
						setPri: 15
						y: yPositionInventory
						cel: itemIcon
						init:
						setScript: showButtons
					)
				else
					(selectedItem
						setPri: 15
						y: yPosition
						cel: 0
						init:
						setScript: showButtons
					)
				)
			)
		)
	)
)

(instance PnCMenuScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== gLayout 1)
			(switch movingButtons
				(0
					(cond 
						(
							(or
								(== yPosition 0)
								(== yPosition 400)
							)
							(if (== gShowMenu 1)
								(= yPosition 0)
								(= movingButtons 1)
							)
						)
						((>= yPosition 20)
							(cond 
								((== gShowMenu 0)
									(= movingButtons 2)
								)
								((>= menuTime howLong)
									(= doMenuTimer 0)
									(= gShowMenu 0)
									(= menuTime 0)
								)
								(else
									(++ menuTime)
								)
							)
						)
					)
				)
				(1
					(if (>= yPosition 25) (= movingButtons 0))
				)
			)
		)
		(selectedItem loop: 2 cel: itemIcon)
		(blockIcon view: 950 loop: 2 cel: 0) ;inv item background cel
		(switch theCursor
			(999
				(walkIcon loop: 1)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(gunIcon loop: 0)
				(invIcon loop: 0)
			)
			(998
				(walkIcon loop: 0)
				(lookIcon loop: 1)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(gunIcon loop: 0)
				(invIcon loop: 0)
			)
			(995
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 1)
				(talkIcon loop: 0)
				(gunIcon loop: 0)
				(invIcon loop: 0)
			)
			(996
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 1)
				(gunIcon loop: 0)
				(invIcon loop: 0)
			)
			(990
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(gunIcon loop: 1)
				(invIcon loop: 0)
			)
			(100
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(gunIcon loop: 1)
				(invIcon loop: 0)
			)
			(itemIcon
				(walkIcon loop: 0)
				(lookIcon loop: 0) ;init:)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(gunIcon loop: 0)
				(invIcon loop: 1)
			)
		)
		(if (!= itemIcon 900)
			(selectedItem
				view: (+ itemIcon 700) ;custom inv views start at 800
				loop: 1
				cel: 0
			)
		else
			(selectedItem view: 950 loop: 2 cel: 0) ;inital state, no item chosen	
		)
	)
	
	(method (handleEvent event &tmp haveMouse)
		(super handleEvent: event)
		(= haveMouse (HaveMouse)) ;fix rightclick crash in scummvm
		(if (== (event type?) evMOUSEBUTTON)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			;(blockIcon setCel: itemIcon) ; added to keep menu item up to date
			(if (& (event modifiers?) emRIGHT_BUTTON)
				(if (== programControl 1)
					(event claimed: 1)
				else
					(event claimed: 1)
					(= menuTime 0)
					(switch theCursor
						(itemIcon
							(theGame setCursor: 999 haveMouse)
						)
						(999 ;walk
							(if gunDrawn
								(theGame setCursor: 990 haveMouse)
							else
								(theGame setCursor: 998 haveMouse)
							)
						)
						(996 ;talk
							(if gunDrawAllowed
								(theGame setCursor: 990 haveMouse)
							else
								(if
									(or 
										(== itemIcon 900)
										(== itemIcon 993)
									)
									(theGame setCursor: 999 haveMouse)
								else
									(theGame setCursor: itemIcon haveMouse)
									(= theCursor itemIcon)
								)
							)
						)
						(998 ;eye
							(theGame setCursor: 995 haveMouse)
						)
						(995 ;hand
							(theGame setCursor: 996 haveMouse)
						)
						(994 ;gun target
							(theGame setCursor: 999 haveMouse)
						)
						(991 ;Salir/exit
							(if gunDrawn
								(theGame setCursor: 990 haveMouse)
							else
								(theGame setCursor: 998 haveMouse)
							)
						)
						(990
							(if gunDrawn
								(theGame setCursor: 994 haveMouse)
							else
								(if
									(or 
										(== itemIcon 900)
										(== itemIcon 993)
									)
									(theGame setCursor: 999 haveMouse)
								else
									(theGame setCursor: itemIcon haveMouse)
									(= theCursor itemIcon)
								)
							)
						)
;;;						(110 ;Kit
;;;							
;;;						)
						(else
							(theGame setCursor: 999 haveMouse)
						)
					)
				)
			)
			(if (not (& (event modifiers?) emRIGHT_BUTTON))
				(cond 
					((== programControl 1) (event claimed: 1))
					((< (event y?) 1)
						(event type: 1 claimed: 1)
						(switch movingButtons
							(0
								(if (== gLayout 1)
									(if (== gShowMenu 1)
										(= gShowMenu 0)
									else
										(= gShowMenu 1)
									)
								)
							)
							(1 (= movingButtons 2) (= gShowMenu 0))
							(2 (= movingButtons 1) (= gShowMenu 1))
						)
					)
					(
						(and
							(> (event x?) (walkIcon nsLeft?))
							(< (event x?) (walkIcon nsRight?))
							(> (event y?) (walkIcon nsTop?))
							(< (event y?) (walkIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 999 haveMouse)
					)
					(
						(and
							(> (event x?) (talkIcon nsLeft?))
							(< (event x?) (talkIcon nsRight?))
							(> (event y?) (talkIcon nsTop?))
							(< (event y?) (talkIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 996 haveMouse)
					)
					(
						(and
							(> (event x?) (lookIcon nsLeft?))
							(< (event x?) (lookIcon nsRight?))
							(> (event y?) (lookIcon nsTop?))
							(< (event y?) (lookIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 998 haveMouse)
					)
					(
						(and
							(> (event x?) (handIcon nsLeft?))
							(< (event x?) (handIcon nsRight?))
							(> (event y?) (handIcon nsTop?))
							(< (event y?) (handIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 995 haveMouse)
					)
					((ClickedOnObj gunIcon (event x?) (event y?))
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 990 haveMouse)
					)
;;;					(
;;;						(and
;;;							(> (event x?) (background nsLeft?))
;;;							(< (event x?) (background nsRight?))
;;;							(> (event y?) (background nsTop?))
;;;							(< (event y?) (background nsBottom?))
;;;						)
;;;						(event claimed: 1)
;;;						(if (and (== gLayout 1) (!= movingButtons 2))
;;;							(= movingButtons 2)
;;;							(= gShowMenu 0)
;;;						)
;;;						(= menuTime 0)
;;;						
;;;					)
					(
						(and
							(> (event x?) (invIcon nsLeft?))
							(< (event x?) (invIcon nsRight?))
							(> (event y?) (invIcon nsTop?))
							(< (event y?) (invIcon nsBottom?))
						)
						(event claimed: 1)
;;;						(if (and (== gLayout 1) (!= movingButtons 2))
;;;							(= movingButtons 2)
;;;							(= gShowMenu 0)
;;;						)
;;;						(= menuTime 0)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(invIcon setScript: dotheinv)
					)
					((ClickedOnObj selectedItem (event x?) (event y?))
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(if (!= itemIcon 900)
							(theGame setCursor: itemIcon haveMouse)
							(= theCursor itemIcon)
						else
							(Print {You must first selected an item.}) ;English
;;;							(Print {Primero debes elegir un objeto.}) ;Spanish
						)
					)
					(
						(and
							(> (event x?) (levelsIcon nsLeft?))
							(< (event x?) (levelsIcon nsRight?))
							(> (event y?) (levelsIcon nsTop?))
							(< (event y?) (levelsIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(invIcon setScript: dothelevels)
					)
;;;					(
;;;						(and
;;;							(> (event x?) (restartIcon nsLeft?))
;;;							(< (event x?) (restartIcon nsRight?))
;;;							(> (event y?) (restartIcon nsTop?))
;;;							(< (event y?) (restartIcon nsBottom?))
;;;						)
;;;						(event claimed: 1)
;;;						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
;;;						(= menuTime 0)
;;;						(restartIcon setScript: dotherestart)
;;;					)
;;;					(
;;;						(and
;;;							(> (event x?) (loadIcon nsLeft?))
;;;							(< (event x?) (loadIcon nsRight?))
;;;							(> (event y?) (loadIcon nsTop?))
;;;							(< (event y?) (loadIcon nsBottom?))
;;;						)
;;;						(event claimed: 1)
;;;						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
;;;						(= menuTime 0)
;;;						(loadIcon setScript: dotheload)
;;;					)
					(
						(and
							(> (event x?) (saveIcon nsLeft?))
							(< (event x?) (saveIcon nsRight?))
							(> (event y?) (saveIcon nsTop?))
							(< (event y?) (saveIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(saveIcon setScript: dothesave)
					)
					(
						(and
							(> (event x?) (quitIcon nsLeft?))
							(< (event x?) (quitIcon nsRight?))
							(> (event y?) (quitIcon nsTop?))
							(< (event y?) (quitIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(quitIcon setScript: dothequit)
					)
					((== fieldKitOpen TRUE) ;when field kit is open check for clicks on field kit	
						(if (ClickedInRect 83 113 48 53 event) ;VIAL
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 7) ;look vial
								)
								(995 ;hand
									(theGame setCursor: 204 haveMouse) ;switch to vial 
								)
								(else
									(event claimed: FALSE)
								)
							)
						)						
						(if (ClickedInRect 61 100 51 57 event) ;Fingerprint Brush
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 10) ;look Fingerprint Brush
								)
								(995 ;hand
									(theGame setCursor: 207 haveMouse) ;switch Fingerprint Brush 
								)
								(else
									(event claimed: FALSE)
								)
							)
						)						
						(if (ClickedInRect 40 78 64 73 event) ;fieldkit camera
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 5) ;look camera
								)
								(995 ;hand
									(theGame setCursor: 202 haveMouse) ;swtich to camera
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						(if (ClickedInRect 100 118 53 73 event) ;Fingerprint Powder
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 9) ;Fingerprints leave oily residue. The powder clings to this residue.
								)
								(995 ;hand
									(theGame setCursor: 206 haveMouse) ;Fingerprint Powder Polvo para huellas dactilares
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						(if (ClickedInRect 58 80 44 49 event) ;Eye dropper
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 8) ;Eye dropper
								)
								(995 ;hand
									(theGame setCursor: 205 haveMouse) ;Eye dropper
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						(if (ClickedInRect 81 107 32 45 event) ;Esparadrapo Casting Plaster
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 6) ;Casting Plaster
								)
								(995 ;hand
									(theGame setCursor: 203 haveMouse) ;Casting Plaster
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						(if (ClickedInRect 51 76 32 42 event) ;bolsa
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 4) ;bolsa
								)
								(995 ;hand
									(theGame setCursor: 201 haveMouse) ;bolsa
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						(if (ClickedInRect 43 57 46 55 event) ;Fingerprint Tape
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 153 11) ;Fingerprint Tape
								)
								(995 ;hand
									(theGame setCursor: 208 haveMouse) ;Fingerprint Tape
								)
								(else
									(event claimed: FALSE)
								)
							)
						)							
						(if (ClickedInRect 130 139 21 29 event) ;Close
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(= newEvent (Event new:))
									(newEvent
									    type: evKEYBOARD
									    message: {close briefcase}
									    modifiers: 999
									    claimed: 0
									)
									(User handleEvent: newEvent)
									(newEvent dispose:) 
								)
								(995 ;hand
									(= newEvent (Event new:))
									(newEvent
									    type: evKEYBOARD
									    message: {close briefcase}
									    modifiers: 999
									    claimed: 0
									)
									(User handleEvent: newEvent)
									(newEvent dispose:) 
								)
								(else
									(event claimed: FALSE)
								)
							)
						)						
;;;						(Printf {x: %d, y: %d, fieldkitopen: %d} (event x?) (event y?) fieldKitOpen) ;for testing - to find rect	
					)
					((ClickedOnObj ego (event x?) (event y?)) ;clicked on sonny
						(if (<= theCursor 137) ;inventory item clicked on sonny
							
							(if
								(and 
									(!= curRoomNum 31) ;not marie house outside
									(!= theCursor 136) ;not marienote
								)
							)
							(event claimed: TRUE)
							((Inventory at: (- theCursor 100)) showSelf:)
						)	
					)
					;airplane override
					(
						(and 
							(== (event claimed?) FALSE)
							(or
								(== curRoomNum 40)
								(== curRoomNum 41)
								(== curRoomNum 42)
								(== curRoomNum 43)
							)
						)
						;ignore so clicks pass to jet script 77
					)
					;Room defaults if nothing else is clicked on.
					(else
						(switch theCursor
							(999 ;walk
								(event type: 1 claimed: 0)
							)
							(998 ;look
								(event type: 1 claimed: 1)
								(switch (Random 42 44)
									(42 (Print {It's just as it appears.}))
									(43 (Print {It doesn't look interesting.}))
									(44 (Print {You see nothing special.}))

;;;									(42 (Print {Es lo que parece.}))
;;;									(43 (Print {No parece interesante.}))
;;;									(44 (Print {No tiene nada de especial.}))
								)
							)
							(996 ;talk 
								(event type: 1 claimed: 1)
								(Print {(There is no response.)} #at -1 144) ;"(There is no response.)"
;;;								(Print {(No hay respuesta.)} #at -1 144) ;"(There is no response.)"
							)
							(995 ;hand
								(event type: 1 claimed: 1)
								(Print {(What do you want to take?.)}) ;"What do you want to take?"
;;;								(Print {(^Qu+ quieres coger?)}) ;"What do you want to take?"
							)
							(997 ;wait sierra
								(event type: 1 claimed: 1)
							)
							(990 ;clicked anywhere with gun
								(if gunDrawAllowed
									(if (curRoom script?)
										(if (not (== 0 (StrCmp ((curRoom script?) name?) {boothScript}))) ;not in shooting range booth
											(event claimed: TRUE)
											(draw)
										)
									else
										(event claimed: TRUE)
										(draw)
									)
								else
									(Print 0 34)
								)
							)
							(100 ;or with gun inventory item 
								(if gunDrawAllowed
									(if (curRoom script?)
										(if (not (== 0 (StrCmp ((curRoom script?) name?) {boothScript}))) ;not in shooting range booth
											(event claimed: TRUE)
											(draw)
										)
									else
										(event claimed: TRUE)
										(draw)
									)
								else
									(Print 0 34)
								)
							)
							(994 ;gun target
								(if (curRoom script?)
									(if (not (== 0 (StrCmp ((curRoom script?) name?) {boothScript})))
										(event claimed: TRUE)
										(fire)
									)
								)
							)
							(else ;inventory item
								(event type: 1 claimed: 1)
								(Print {no need to use that here.}) ;"no need to use that here"
;;;								(Print {No hay ninguna necesidad de usar esto ah|.}) ;"no need to use that here"
							)
						)
					)
				)
			)
		)
	)
)

(instance dothequit of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(= state newState)
		(switch state
			(0
				(quitIcon loop: 1)
				(= cycles 3)
				(= tempCur theCursor)
				(theGame setCursor: 993 (HaveMouse)) ;pointer
			)
			(1
				(= temp0
					(Print
						997 5
;;;						{^De verdad quieres salir?}
						#title {Quit} ;English
;;;						#title {Salir} ;Spanish
						#icon 999 0 0
						#font 1
						#button { Quit_} 1 ;English
						#button { Oops } 0 ;English
;;;						#button { Salir_} 1 ;Spanish
;;;						#button { Ups } 0 ;Spanish
					)
					;;;				(= quit
;;;					(Print 997 5
;;;						#button {Quit} 1
;;;						#button {Continue} 0
;;;						#icon 999 0 0
;;;					)
;;;				)
					
					
				)
				(if (== temp0 1)
					(= quit 1)
				else
					(quitIcon loop: 0)
					(theGame setCursor: tempCur (HaveMouse))
				)
			)
		)
	)
)

(instance dothesave of Script
	(properties)
	
	(method (changeState newState &tmp loadOrSave)
		(= state newState)
		(switch state
			(0
				(saveIcon loop: 1) ;change to icon "clicked"
				(= cycles 3) ;wait 3 cycles, then goto next state.
				(= tempCur theCursor)
				(theGame setCursor: 993 (HaveMouse)) ;pointer
			)
			(1
				(= loadOrSave 
					(Print
;;;						950 30 
						{Save or Load:}
						#button {\n__SAVE__\n_} 1 ;#button (string) (pickedLoad==1) ;ENGLISH
						#button {\n__LOAD__\n_} 2 ;(pickedLoad=0) ;ENGLISH
;;;						#button {\n__SALVAR__\n_} 1 ;#button (string) (pickedLoad==1) ;SPANISH
;;;						#button {\n__CARGAR__\n_} 2 ;(pickedLoad=0) ;SPANISH
					)
				)
				(switch loadOrSave
					(0
						(= cycles 1)
					)
						
					(1
						(Bset fPnCSaveFlag) ;ScummVM doesn't trigger saves from pnc menu, trigger from main instead.
						(= cycles 1)
					)
					(2
						(theGame restore:)
						(= cycles 1)
					)
					(else
						(Bset fPnCSaveFlag)
						(= cycles 1)
					)
				)
			)
			(2
				(saveIcon loop: 0)
				(theGame setCursor: tempCur (HaveMouse))
			)
		)
	)
)

;;;(instance dotherestart of Script
;;;	(properties)
;;;	
;;;	(method (changeState newState)
;;;		(= state newState)
;;;		(switch state
;;;			(0
;;;				(restartIcon loop: 1)
;;;				(= cycles 3)
;;;			)
;;;			(1
;;;				(if
;;;					(Print MENU 9
;;;;;;	 					#title {Restart} ;ENGLISH
;;;	 					#title {Reiniciar} ;SPANISH
;;;
;;;						#font bigFont
;;;;;;						#button {Restart} 1 ;ENGLISH
;;;;;;						#button { Oops_} 0 ;ENGLISH
;;;						#button {Reiniciar} 1 ;SPANISH
;;;						#button { Ups_} 0 ;Spanish			
;;;					)
;;;					(theGame restart:)
;;;				else
;;;					(restartIcon loop: 0)
;;;					(= newState 0)
;;;				)
;;;			)
;;;		)
;;;	)
;;;)

(instance dothelevels of Script
	(properties)
	
	(method (changeState newState &tmp [str 220] newTextColor newBackColor)
		(= state newState)
		(switch state
			(0
				(levelsIcon loop: 1)
				(= cycles 3)
				(= tempCur theCursor)
				(theGame setCursor: 993 (HaveMouse)) ;pointer
			)
			(1
				(= sGauge2
					(PrintSpecialSimple
;;;						950 33 ;"configuration:" 
						{config:}
						#button {\n_SPEED_\n_} 1 ;#button (string) (pickedLoad==1) ;ENGLISH
						#button {\n_VOLUME_\n_} 2 ;(pickedLoad=0) ;ENGLISH
;;;						#button {\n_VELOCIDAD_\n_} 1 ;#button (string) (pickedLoad==1) ;SPANISH
;;;						#button {\n_VOLUMEN_\n_} 2 ;(pickedLoad=0) ;SPANISH
						;#button {\n_INSULT_\n_} 3 ;ENGLISH
						;#button {\n_AUTOSAVE_\n_} 4 ;ENGLISH
						#button {\n_ABOUT_\n_} 5 ;ENGLISH
						#button {\n__SCORE__\n_} 6 ;ENGLISH
						;#button {\n_INSULTO_\n_} 3 ;SPANISH
						;#button {\n_AUTOSALVAR_\n_} 4 ;SPANISH
;;;						#button {\n__CREDITOS_\n_} 5 ;SPANISH
;;;						#button {\n__PUNTOS__\n_} 6 ;SPANISH

					)
				)
				(switch sGauge2
					(1
						(= sGauge 1)
						(= state 9)	
					)
					(2
						(= vGauge 2)
						(= state 19)
					)
					(3
						(= state 29)
					)
					(4
						(= state 39)
					)
					(5
						(= state 49)
					)
					(6
						(= state 59)
					)
					(else
						(= state 99)
					)

				)
				(= cycles 1)
			)
			(10
				(if (HaveMem GaugeSize)
					(= pncSpeed
						((Gauge new:)
							description:
;;;								(Format @str 950 34)
								(Format @str {Use the mouse and the left and right arrow keys to adjust the speed.})
							text: {Animation Speed} ;ENGLISH
;;;							text: {Velocidad del Juego} ;SPANISH
							normal: 10
							higher: {Faster} ;ENGLISH
							lower: {Slower} ;ENGLISH
;;;							higher: {Subir} ;SPANISH
;;;							lower: {Bajar} ;SPANISH
							doit: (- 16 speed)
						)
					)
					(theGame setSpeed: (- 16 pncSpeed))
					(DisposeScript GAUGE)
				)
				(= state 99)
				(= cycles 1)
			)
			(20
				(if (HaveMem GaugeSize)
					(= pncVolume
						((Gauge new:)
							description:
;;;								(Format @str 950 35)
								(Format @str {Use the mouse and the left and right arrow keys to adjust the volume.})
							text: {Sound Volume} ;ENGLISH
;;;							text: {Volumen} ;SPANISH
							normal: 15
							higher: {Louder} ;ENGLISH
							lower: {Softer} ;ENGLISH
;;;							higher: {Subir} ;SPANISH
;;;							lower: {Bajar} ;SPANISH	
							doit: (DoSound ChangeVolume pncVolume)
						)
					)
					(Bset fPnCVolumeFlag)
					(DisposeScript GAUGE)
					(= state 99)
					(= cycles 1)
				)	
			)
			(50
				;credits
				(Print
					(Format @str 997 0 version)
					#title {__Police Quest II Credits__}
;;;					#title {__Cr+ditos de Police Quest II__}					
					#mode teJustCenter
					#width 160
					#font smallFont
					#icon 999 2 0
				)
				(Print
					(Format @str 997 1)
					#title {And last, but not least...}
;;;					#title {Y por {ltimo, aunque no menos importante...}
					#mode teJustCenter
					#width 180
					#font smallFont
					#icon 999 2 0
				)
				(Print
					(Format @str 997 7)
					#title {Version Point and Click}
;;;					#title {Versi/n Point and Click}
					#mode teJustCenter
					#width 180
					#font smallFont
					#icon 905 0 0
				)				
				(= state 99)
				(= cycles 1)
			)
			(60 ;score
;;;				(Print (Format @str 0 0 score possibleScore))
				(Print (Format @str {Score: %d of %d  \n\n  Police Quest II -Point and Click-} score possibleScore))
				(= state 99)
				(= cycles 1)
			)
			(100
				(levelsIcon loop: 0)
				(theGame setCursor: tempCur (HaveMouse))
			)
		)
	)
)

(instance dotheinv of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(if (== canTab 1)
					(= gPreviousCursor theCursor)
					(invIcon loop: 1)
					(= cycles 3)
				else
					(Print 950 9)
				)
			)
			(1 (= doInventory 1)
				(if (HaveMem 1024) ;InvSize)
				(invIcon loop: 0)
					(inventory showSelf: ego)
				)
			)
		)
	)
)

(instance walkIcon of Prop
	(properties
		y 6
		x 15 ;14
		view 950
		loop 0
		cel 0
	)
)

(instance lookIcon of Prop
	(properties
		y 6
		x 46
		view 950
		cel 1
		loop 0
	)
)

(instance handIcon of Prop
		(properties
		y 6
		x 77	
		view 950
		cel 2
		loop 0
	)
)

(instance talkIcon of Prop
	(properties
		y 6
		x 108
		view 950
		cel 3
		loop 0
	)
)

(instance gunIcon of Prop
	(properties
		y 6 ;189
		x 139 ;move the rest of the icons down (x + 30)
		view 950
		cel 4
		loop 0
	)
)

;;;(instance background of Prop
;;;	(properties
;;;		y 6
;;;		x 169 ;197 ;move the rest of the icons down (x + 30)
;;;		view 950
;;;		cel 0
;;;		loop 7
;;;
;;;	)
;;;)

(instance invIcon of Prop
	(properties
		y 6
		x 211 ;248 ;138 ;127 ;move the rest of the icons down (x + 28)
		view 950
		cel 5
		loop 0
	)
)

(instance blockIcon of Prop
	(properties
		y 6
		x 176 ;171 ;;as selectedicon
		view 950
		loop 2
		cel 0
	)
)

(instance levelsIcon of Prop
	(properties
		y 6
		x 242 ;220
		view 950
		cel 6
		loop 0
	)
)

;combine with save?
;;;(instance restartIcon of Prop
;;;	(properties
;;;		y 6
;;;		x 248
;;;		view 950
;;;		cel 7
;;;		loop 0
;;;	)
;;;)

;;;(instance loadIcon of Prop ;combine load/save icons to make room for smell
;;;	(properties
;;;		y 189
;;;		x 250
;;;		view 950
;;;		cel 7
;;;		loop 0
;;;	)
;;;)

(instance saveIcon of Prop
	(properties
		y 6
		x 273
		view 950
		cel 8
		loop 0
	)
)

(instance quitIcon of Prop
	(properties
		y 6
		x 304
		view 950
		cel 9
		loop 0
	)
)

(instance selectedItem of Prop
	(properties
		y 6
		x 176 ;as bloxk icon;
		view 950 ;950
		loop 3; ;3
		cel 0
	)
)

(instance showButtons of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(switch movingButtons
			(0)
			(1
				(if (< yPosition 25)
					;(= yPosition (+ yPosition yIconStep))
					(= yPosition 25)
					(= menuTime 0)
				else
					(= movingButtons 0)
					(= doMenuTimer 1)
				)
			)
			(2
				(if (and (> yPosition 0) (< yPosition 400))
					(= yPosition (- yPosition yIconStep))
				else
					(= movingButtons 0)
					(= yPosition 400)
				)
			)
		)
		(walkIcon y: yPosition)
		(lookIcon y: yPosition)
		(handIcon y: yPosition)
		(talkIcon y: yPosition)
		(gunIcon y: yPosition)
		;(background y: yPosition)
		(invIcon y: yPosition)
		(blockIcon y: yPosition)
		(levelsIcon y: yPosition)
		;(restartIcon y: yPosition)
		;(loadIcon y: yPosition)
		(saveIcon y: yPosition)
		(quitIcon y: yPosition)
		(if (!= itemIcon 950)
			(selectedItem y: yPosition)
		else
			(selectedItem y: yPosition)
		)
	)
)
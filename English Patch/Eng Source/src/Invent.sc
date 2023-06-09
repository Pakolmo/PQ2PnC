;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INVENT.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Bob Heitman
;;;;
;;;;	Classes to manage inventory (items in the possession of other objects
;;;;	in an adventure game).
;;;;
;;;;	Classes:
;;;;		InvItem
;;;;		Inventory
;;;;
;;;;	Objects:
;;;;		invDialog


(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use System)
(use PncMenu)
(use Gun)
(use kitRegion)

(local
	yesI
	selI
	lookI
	useI
	pigAppears
	[msgBuf 33]
	[titleBuf 22]
	oldCur
	
)

; a stock list will be able to handle the scanning required to:
; find items by parsed name (firstTrue: #saidMe:)
; build status dialog (first, next)
; find items in room (firstTrue: #ownedBy)
; reference items by number (at: enumName)



(class InvItem kindof Object
	;;; An InvItem is something which can be owned by an object in an
	;;; adventure game.

	(properties
		-info- $8004		;(| CLASSBIT NODISPLAY)
		name "InvI"			;my literal name
		said 0				;said spec which user can type to identify this item
		description 0		;long text description
		owner 0				;who owns this item
		view 0				;picture of the item
		loop 0
		cel 0
		script 0				;a script that can control the item
	)

;;;	(methods
;;;		saidMe				;does user input match the said spec?
;;;		ownedBy				;return TRUE if owned by given object
;;;		showSelf				;display this item
;;;		moveTo				;change ownership of this item
;;;		changeState
;;;	)



	(method (saidMe)
		;; Return TRUE if my "said" was "parsed"

		(return (Said said))
	)


	(method (ownedBy id)
		;; Return TRUE if owned by this ID.

		(return (== owner id))
	)
	

	(method (moveTo id)
		;; Set my "owner" to passed ID.

		(= owner id)
		(return self)
	)


	(method (showSelf)
		;; Display this object.

		(ShowView 
			(if description
				description

			else
				name

			)
			view loop cel
		)
	)


	(method (changeState newState)
		(if script
			(script changeState:newState)
		)
	)
)




(class Inventory kindof Set
	;;; This is the set of all inventory items in the game.

	(properties
		name "Inv"
		carrying	"You are carrying:" ;ENGLISH
;;;		carrying	"Llevas contigo:" ;SPANISH
			;Title of the inventory display when the object in question
			;has some inventory items.
		empty "You are carrying nothing!" ;ENGLISH
;;;		empty "^No llevas nada!" ;SPANISH
			;Title of the inventory display when the object in question
			;has no inventory items.
	)

;;;	(methods
;;;		showSelf			;display inventory owned by an object
;;;		saidMe			;return InvItem matching user input
;;;		ownedBy			;return InvItem owned by an object
;;;	)


	(method (init)
		(= inventory self)
	)


	(method (saidMe)
		;; Return the ID of the first item in the inventory whose said
		;; spec matches user input.

		(return (self firstTrue: #saidMe:))
	)


	(method (ownedBy whom)
		;; Return the first item in inventory which is owned by 'whom'.

		(return (self firstTrue: #ownedBy: whom))
	)


	(method (showSelf whom)
		;; Show the possessions of 'whom'.
	
		(invDialog text:carrying, doit:whom)
	)
)



(instance invDialog of Dialog
	(properties
		name "invD"
	)


	(method (init whom &tmp lastX lastY widest num el node obj)
		(= oldCur theCursor)
		;Init positioning vars.
		(= widest (= lastX (= lastY MARGIN)))
		(= num 0)

		(for 	((= node (inventory first:)))
				node
				((= node (inventory next: node)))

			(= obj (NodeValue node))
	
			;Does this character own this thing.
			(if (obj ownedBy: whom)	
				(++ num)
				(self add: 
					((= el (DText new:))
						value: obj ; obj, 
						text: (obj name?), 
						nsLeft: lastX, 
						nsTop: lastY,
						state:(| dActive dExit),
						font: smallFont,
						setSize:,
						yourself:
					)
				)

				;Keep track of widest item.
				(if (< widest (- (el nsRight?) (el nsLeft?)))
					(=  widest (- (el nsRight?) (el nsLeft?)))
				)

				;Bump lastY by height of character this item.
				(+= lastY (+ (- (el nsBottom?) (el nsTop?)) 1))

				;Wrap to next column.
				(if (> lastY 140)
					(= lastY MARGIN)
					(+= lastX (+ widest 10))
					(= widest 0)
				)
			)
		)
 
		;If no items owned, bag it.
		(if (not num)
			(self dispose:)
			(return 0)
		)

		; give ourself the class SysWindow as our window
		(= window SysWindow)

		;Size dialog and add button to lower right
		(self setSize:)
		(= selI (DButton new:))
		(selI 
			text: "Select", ;ENGLISH
;;;			text: "Elegir", ;SPANISH
			setSize:,
			moveTo: 
				;(- nsRight (+ MARGIN (yesI nsRight?)))
				(+ nsLeft (+ MARGIN (selI nsLeft?)))
				nsBottom
		)
		(= lookI (DButton new:))
		(lookI 
			text: "Look_", ;English
;;;			text: "Mirar", ;Spanish
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (selI nsRight?)))
				nsBottom
		)
		(= useI (DButton new:))
		(useI 
			text: "Use", ;ENGLISH
;;;			text: "Usar", ;SPANISH
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (lookI nsRight?)))
				nsBottom
		)
		(= yesI (DButton new:))
		(yesI 
			text: "OK", 
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (useI nsRight?) 5))
				nsBottom
		)

		;Add button and resize the dialog.
		(self add: selI lookI useI yesI, setSize:, center:)

		(return num)
	)

	(method (doit whom &tmp el [str3 20])
		;Initialize the dialog. If we have nothing, tell user.
		(if (not (self init: whom))
			(Print (inventory empty?))
			(return)
		)
	
		;Call the dialog with exit as default
		(self open: wTitled 15)
		(= el yesI)
		
		(theGame setCursor: 993) ;start with select arrow
		
		(repeat
			(= el (super doit:el))

			;These returns signal end of dialog
			(if (or (not el) (== el -1) (== el yesI))
				(if
					(or
						(== theCursor 998)
						(== theCursor 993)
					) 
					(= theCursor oldCur)
					(theGame setCursor: oldCur (HaveMouse))
				)
				(break)
			)
			
			(if
				(or
					(== el selI)
					(== el lookI)
					(== el useI)
					(== el yesI) 
				)
				(cond 
					((== el selI)
						(if (!= theCursor 993)
							(theGame setCursor: 993)
						)
					)
					((== el lookI)
						(if (!= theCursor 998)
							(theGame setCursor: 998)
						)
					)
					((== el useI)
						(if (!= theCursor 995)
							(theGame setCursor: 995)
						)
					)
					(else
						;do nothing
					)
				)
			else	
				(switch theCursor
					(993 ;set cursor to selected item and print desc.
						(= itemIcon ((el value?) view?))
						(theGame setCursor: ((el value?) view?))
					)
					(995 ;use
						(cond
							((== ((el value?) view?) 133) ;used bombinstructions
									(Print 800 53 #font smallFont)		
							)
							((== ((el value?) view?) 132) ;used gas mask
								(if (== methaneGasTimer -1)
									(Print 205 31) ;You don't need to wear the mask now.
								else
									(if wearingGasMask
										(Print 205 32)
									else
										(= wearingGasMask TRUE)
										(if (== (ego view?) 0)
											(ego view: 296)
										else
											(ego view: 306)
										)
									)
								)
							)
							((== ((el value?) view?) 100) ;used handgun
						 		(if
						 			(and
						 				(== curRoomNum 10)
						 				(curRoom script?)
									)
						 			(if (== 0 (StrCmp ((curRoom script?) name?) {boothScript})) ;0 = STRINGS_EQUAL
										(Bset fPnCAdjustSights)
										(= theCursor oldCur)
										(theGame setCursor: oldCur (HaveMouse))
										(break)
									else
										(Print {Wait until you're in the booth to adjust the gun sights.})
;;;										(Print {Espera hasta que est+s en la cabina para ajustar la mira del arma.})
									)
								else
									(Print {You can only adjust the gun sights at the shooting range.})
;;;									(Print {S/lo puedes ajustar las miras del arma en el campo de tiro.})
								)
							)
							((== ((el value?) view?) 101) ;used extra clips/reload alternate of using clips on gun to reload
						 		(load)
							)
							((== ((el value?) view?) 137) ;if your_LPD_buisnesss_card 
								(Print {You flip the buisness card over.})
;;;								(Print {Giras tu tarjeta de negocios.})
								(if (not (Btst fDiscoveredLockerCombo))
									(Print {Your locker combination is on the back of the card.})
;;;									(Print {La combinaci/n de tu taquilla est* detr*s de la tarjeta.})
								)
								(if (== ((Inventory at: 37) cel?) 0) ;flip each look
									(Bset fDiscoveredLockerCombo)
									((Inventory at: 37) cel: 1) 
								else
									((Inventory at: 37) cel: 0)
								)
								((el value?) showSelf:) 
							)

							((== ((el value?) view?) 135) ;if Colby's buisnesss_card 
								(Print 26 12)
							)
							((== ((el value?) view?) 136) ;if Colby's buisnesss_card 
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
							((== ((el value?) view?) 107) 
								(if (not (ego has: iWallet))
									(DontHave)
								else
									(Print 800 50)
									(SolvePuzzle 2 fFirstFindDivingCard)
									(Bset fFoundDiverCard)
								)
							)
							((== ((el value?) view?) 115) ;ear protectors
								(if (curRoom script?)
									(if (== 0 (StrCmp ((curRoom script?) name?) {boothScript}))
										(cond 
											((not (ego has: iEarProtectors))
												(DontHave)
											)
											(wearingEarProtectors
												(Print 10 67)
											)
											(gunDrawn
												(Print 10 61)
											)
											(else
												(= global210 0)
												(Print {You put on the ear protectors.})
;;;												(Print {Te pones los protectores de o|dos.})
												(= wearingEarProtectors 1)
											)
										)
									else
										(Print 10 30) ;"wear ep when in the booth"
									)
								else
									(Print 10 30)
								)
							)
							((== ((el value?) view?) 110) ;used field Kit
            					(= fieldKitToggle 1)
;;;            						(inventory
;;;										carrying: {You are carrying:}
;;;										empty: {Your Detective's Field Kit is used to carry various things you will need in the course of your investigations: \n\n Plastic Baggie \n Small Camera \n Casting Plaster \n Glass Vial \n Eye Dropper \n Fingerprint Powder\n Fingerprint Brush \n Fingerprint Tape \n}
;;;										showSelf: {Your Detective's Field Kit is used to carry various things you will need in the course of your investigations: \n\n Plastic Baggie \n Small Camera \n Casting Plaster \n Glass Vial \n Eye Dropper \n Fingerprint Powder\n Fingerprint Brush \n Fingerprint Tape \n}
;;;									)
            					(break)								 
							)
							(else
								(Print {You don't need to use that item.})
;;;								(Print {No es necesario utilizar ese objeto.})
							)
						)
					)
					(998 ;look at item
						(if
							(and 
								(curRoom script?)
;;;								(== 0 (StrCmp ((curRoom script?) name?) {boothScript}))
								(== curRoomNum 10)
								(== ((el value?) view?) 100) ;looked at gun during booth script to trigger adjustments sames as hand
							)
							(if (curRoom script?)
								(if (== (StrCmp ((curRoom script?) name?) {boothScript}) 0)
									(Bset fPnCAdjustSights)
									(= theCursor oldCur)
									(theGame setCursor: oldCur (HaveMouse))
									(break)
								else
									((el value?) showSelf:)
								)
							else
								((el value?) showSelf:)
							)
						else
;;;							((el value?) showSelf:) ;display the inventory item normally.
							(if (== ((el value?) view?) 133) ;looked at bombInstructions
								(Print 800 53 #font smallFont)
							else
								((el value?) showSelf:)
							)
						)
					)
					(101 ;extra ammo clips
						(if (== ((el value?) view?) 100) ;clicked gun with clips
							(load)	
						)
					)
					(else
						(Print {You can't use those items together.}) ;English
;;;						(Print {No puedes usar esos dos objetos juntos.}) ;Spanish
					)
				)
			)
		)
		(self dispose:)
	)

		
	(method (handleEvent event &tmp msg typ)
		(= msg (event message?))
		(= typ (event type?))

		(switch typ
			(keyDown
				(switch msg
					(UPARROW
						(= msg SHIFTTAB)
					)
					(DOWNARROW
						(= msg TAB)
					)
				)
			)
			(direction
				(switch msg
					(dirN
						(= msg SHIFTTAB)
						(= typ keyDown)
					)
					(dirS
						(= msg TAB)
						(= typ keyDown)
					)
				)
			)
		)
		(event
			type: typ,
			message: msg
		)
		(return (super handleEvent:event))
	)
)


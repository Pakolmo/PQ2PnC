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

;;;
;;;(script# INVENT)
;;;(include game.sh)
;;;(use Main)
;;;(use Intrface)
;;;(use Save)
;;;(use System)
;;;
;;;(local
;;;	yesI
;;;)
;;;
;;;; a stock list will be able to handle the scanning required to:
;;;; find items by parsed name (firstTrue: #saidMe:)
;;;; build status dialog (first, next)
;;;; find items in room (firstTrue: #ownedBy)
;;;; reference items by number (at: enumName)
;;;
;;;
;;;
;;;(class InvItem kindof Object
;;;	;;; An InvItem is something which can be owned by an object in an
;;;	;;; adventure game.
;;;
;;;	(properties
;;;		-info- $8004		;(| CLASSBIT NODISPLAY)
;;;		name "InvI"			;my literal name
;;;		said 0				;said spec which user can type to identify this item
;;;		description 0		;long text description
;;;		owner 0				;who owns this item
;;;		view 0				;picture of the item
;;;		loop 0
;;;		cel 0
;;;	)
;;;
;;;;;;	(methods
;;;;;;		saidMe				;does user input match the said spec?
;;;;;;		ownedBy				;return TRUE if owned by given object
;;;;;;		showSelf				;display this item
;;;;;;		moveTo				;change ownership of this item
;;;;;;		changeState
;;;;;;	)
;;;
;;;
;;;
;;;	(method (saidMe)
;;;		;; Return TRUE if my "said" was "parsed"
;;;
;;;		(return (Said said))
;;;	)
;;;
;;;
;;;	(method (ownedBy id)
;;;		;; Return TRUE if owned by this ID.
;;;
;;;		(return (== owner id))
;;;	)
;;;	
;;;
;;;	(method (moveTo id)
;;;		;; Set my "owner" to passed ID.
;;;
;;;		(= owner id)
;;;		(return self)
;;;	)
;;;
;;;
;;;	(method (showSelf)
;;;		;; Display this object.
;;;
;;;		(ShowView 
;;;			(if description
;;;				description
;;;			else
;;;				name
;;;			)
;;;			view loop cel
;;;		)
;;;	)
;;;)
;;;
;;;
;;;(class Inventory kindof Set
;;;	;;; This is the set of all inventory items in the game.
;;;
;;;	(properties
;;;		name "Inv"
;;;		carrying	"You are carrying:"
;;;			;Title of the inventory display when the object in question
;;;			;has some inventory items.
;;;		empty "You are carrying nothing!"
;;;			;Title of the inventory display when the object in question
;;;			;has no inventory items.
;;;	)
;;;
;;;;;;	(methods
;;;;;;		showSelf			;display inventory owned by an object
;;;;;;		saidMe			;return InvItem matching user input
;;;;;;		ownedBy			;return InvItem owned by an object
;;;;;;	)
;;;
;;;
;;;	(method (init)
;;;		(= inventory self)
;;;	)
;;;
;;;	
;;;	(method (showSelf whom &tmp temp0 [temp1 30] [str 301] node obj)
;;;		(StrCpy @str carrying)
;;;		(= temp0 0)
;;;		(= node (inventory first:))
;;;		(while node
;;;			(if ((= obj (NodeValue node)) ownedBy: whom)
;;;				(if temp0 (StrCat @str {,}))
;;;				(++ temp0)
;;;				(StrCat @str (Format @temp1 995 0 (obj name?)))
;;;			)
;;;			(= node (inventory next: node))
;;;		)
;;;		(if temp0
;;;			(StrCat @str {.})
;;;			(Print @str)
;;;		else
;;;			(Print empty)
;;;		)
;;;	)
;;;	
;;;	(method (saidMe)
;;;		;; Return the ID of the first item in the inventory whose said
;;;		;; spec matches user input.
;;;
;;;		(return (self firstTrue: #saidMe:))
;;;	)
;;;
;;;
;;;	(method (ownedBy whom)
;;;		;; Return the first item in inventory which is owned by 'whom'.
;;;
;;;		(return (self firstTrue: #ownedBy: whom))
;;;	)
;;;
;;;)
;;;




;;;;;; Sierra Script 1.0 - (do not remove this comment)
;;;(script# 995)
;;;(include sci.sh)
;;;(use Main)
;;;(use Intrface)
;;;(use IconI)
;;;(use SysWindow)
;;;(use System)
;;;
;;;
;;;(local
;;;	local0
;;;)
;;;(procedure (localproc_07be param1 param2 param3 &tmp temp0 newEvent temp2 temp3)
;;;	(= temp3
;;;		(+
;;;			(/ (- (param1 nsRight?) (param1 nsLeft?)) 2)
;;;			(param1 nsLeft?)
;;;		)
;;;	)
;;;	(= temp2 param2)
;;;	(return
;;;		(while (>= (Abs (- temp2 param3)) 4)
;;;			(if
;;;				(= temp0
;;;					(self
;;;						firstTrue:
;;;							#onMe
;;;							((= newEvent (Event new:)) x: temp3 y: temp2 yourself:)
;;;					)
;;;				)
;;;				(newEvent dispose:)
;;;				(return temp0)
;;;			)
;;;			(newEvent dispose:)
;;;			(if (< param2 param3)
;;;				(= temp2 (+ temp2 4))
;;;			else
;;;				(= temp2 (- temp2 4))
;;;			)
;;;		)
;;;	)
;;;)
;;;
;;;(class InvI of IconI
;;;	(properties
;;;		view 0
;;;		loop 0
;;;		cel 0
;;;		nsLeft 0
;;;		nsTop 0
;;;		nsRight 0
;;;		nsBottom 0
;;;		state $0000
;;;		cursor 999
;;;		type $4000
;;;		message 0
;;;		modifiers $0000
;;;		signal $0000
;;;		maskView 0
;;;		maskLoop 0
;;;		maskCel 0
;;;		highlightColor 0
;;;		lowlightColor 0
;;;		noun 0
;;;		modNum 0
;;;		helpVerb 0
;;;		owner 0
;;;		script 0
;;;		value 0
;;;	)
;;;	
;;;	(method (show &tmp [temp0 4])
;;;		(DrawCel view loop cel nsLeft nsTop -1)
;;;	)
;;;	
;;;	(method (highlight param1 &tmp temp0 temp1 temp2 temp3 temp4)
;;;		(if (== highlightColor -1) (return))
;;;		(= temp4
;;;			(if (and argc param1) highlightColor else lowlightColor)
;;;		)
;;;		(= temp0 (- nsTop 2))
;;;		(= temp1 (- nsLeft 2))
;;;		(= temp2 (+ nsBottom 1))
;;;		(= temp3 (+ nsRight 1))
;;;		(Graph grDRAW_LINE temp0 temp1 temp0 temp3 temp4 -1 -1)
;;;		(Graph grDRAW_LINE temp0 temp3 temp2 temp3 temp4 -1 -1)
;;;		(Graph grDRAW_LINE temp2 temp3 temp2 temp1 temp4 -1 -1)
;;;		(Graph grDRAW_LINE temp2 temp1 temp0 temp1 temp4 -1 -1)
;;;		(Graph
;;;			grUPDATE_BOX
;;;			(- nsTop 2)
;;;			(- nsLeft 2)
;;;			(+ nsBottom 2)
;;;			(+ nsRight 2)
;;;			1
;;;		)
;;;	)
;;;	
;;;	(method (onMe param1)
;;;		(return (if (super onMe: param1) (not (& signal $0004)) else 0))
;;;	)
;;;	
;;;	(method (ownedBy param1)
;;;		(return (== owner param1))
;;;	)
;;;	
;;;	(method (moveTo theOwner)
;;;		(= owner theOwner)
;;;		(if (and value (== theOwner gEgo))
;;;			(gGame changeScore: value)
;;;			(= value 0)
;;;		)
;;;		(return self)
;;;	)
;;;	
;;;	(method (doVerb theVerb)
;;;		(if (not modNum) (= modNum gNumber))
;;;		(if
;;;		(and global90 (Message msgGET modNum noun theVerb 0 1))
;;;			(gPq1Messager say: noun theVerb 0 0 0 modNum)
;;;		)
;;;	)
;;;)
;;;
;;;(class Inv of IconBar
;;;	(properties
;;;		elements 0
;;;		size 0
;;;		height 0
;;;		underBits 0
;;;		oldMouseX 0
;;;		oldMouseY 0
;;;		curIcon 0
;;;		highlightedIcon 0
;;;		prevIcon 0
;;;		curInvIcon 0
;;;		useIconItem 0
;;;		helpIconItem 0
;;;		walkIconItem 0
;;;		port 0
;;;		window 0
;;;		state $0400
;;;		activateHeight 0
;;;		y 0
;;;		normalHeading {You are carrying:}
;;;		heading 0
;;;		empty {nothing!}
;;;		iconBarInvItem 0
;;;		okButton 0
;;;		selectIcon 0
;;;	)
;;;	
;;;	(method (init)
;;;		(= heading normalHeading)
;;;	)
;;;	
;;;	(method (doit &tmp temp0 temp1 temp2 [temp3 3] temp6 temp7 temp8 [temp9 50])
;;;		(asm
;;;code_0844:
;;;			pushi    #type
;;;			pushi    0
;;;			pushi    #new
;;;			pushi    0
;;;			class    Event
;;;			send     4
;;;			sat      temp1
;;;			send     4
;;;			bnt      code_085d
;;;			pushi    #dispose
;;;			pushi    0
;;;			lat      temp1
;;;			send     4
;;;			jmp      code_0844
;;;code_085d:
;;;			pushi    #dispose
;;;			pushi    0
;;;			lat      temp1
;;;			send     4
;;;			ldi      0
;;;			sat      temp1
;;;code_0868:
;;;			pTos     state
;;;			ldi      32
;;;			and     
;;;			bnt      code_0ceb
;;;			pushi    #type
;;;			pushi    1
;;;			pushi    0
;;;			pushi    37
;;;			pushi    1
;;;			pushi    0
;;;			pushi    61
;;;			pushi    1
;;;			pushi    0
;;;			pushi    0
;;;			pushi    1
;;;			pushi    0
;;;			pushi    1
;;;			pushi    1
;;;			pushi    0
;;;			pushi    73
;;;			pushi    1
;;;			pushi    0
;;;			pushi    147
;;;			pushi    1
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     42
;;;			pushi    2
;;;			pushi    32767
;;;			lofsa    invEvent
;;;			push    
;;;			callk    GetEvent,  4
;;;			pushi    #x
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			sag      gPEventX
;;;			pushi    #y
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			sag      gPEventY
;;;			ldi      0
;;;			sat      temp8
;;;			pushi    #localize
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			pToa     curIcon
;;;			bnt      code_0942
;;;			pushi    #modifiers
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			not     
;;;			bnt      code_0942
;;;			pTos     curIcon
;;;			pToa     selectIcon
;;;			ne?     
;;;			bnt      code_0942
;;;			pushi    #type
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			ldi      1
;;;			eq?     
;;;			bt       code_0918
;;;			pushi    #type
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			ldi      4
;;;			eq?     
;;;			bnt      code_0903
;;;			pushi    #message
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			ldi      13
;;;			eq?     
;;;			bnt      code_0903
;;;			ldi      1
;;;			sat      temp8
;;;			bt       code_0918
;;;code_0903:
;;;			pushi    #type
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			ldi      256
;;;			eq?     
;;;			bnt      code_0942
;;;			ldi      1
;;;			sat      temp8
;;;			bnt      code_0942
;;;code_0918:
;;;			pTos     curIcon
;;;			pToa     helpIconItem
;;;			ne?     
;;;			bt       code_092c
;;;			pushi    #signal
;;;			pushi    0
;;;			pToa     helpIconItem
;;;			send     4
;;;			push    
;;;			ldi      16
;;;			and     
;;;			bnt      code_0942
;;;code_092c:
;;;			pushi    #type
;;;			pushi    1
;;;			pushi    16384
;;;			pushi    37
;;;			pushi    1
;;;			pushi    #message
;;;			pushi    0
;;;			pToa     curIcon
;;;			send     4
;;;			push    
;;;			lofsa    invEvent
;;;			send     12
;;;code_0942:
;;;			pushi    1
;;;			lofsa    invEvent
;;;			push    
;;;			callk    MapKeyToDir,  2
;;;			pushi    #type
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			sat      temp2
;;;			lag      gNewEventHandler
;;;			bnt      code_0967
;;;			pushi    #handleEvent
;;;			pushi    1
;;;			lofsa    invEvent
;;;			push    
;;;			lag      gNewEventHandler
;;;			send     6
;;;			jmp      code_0ce0
;;;code_0967:
;;;			lst      temp2
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_098a
;;;			pushi    #modifiers
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			bnt      code_098a
;;;			pushi    #advanceCurIcon
;;;			pushi    0
;;;			self     4
;;;			pushi    #claimed
;;;			pushi    1
;;;			pushi    1
;;;			lofsa    invEvent
;;;			send     6
;;;			jmp      code_0ce0
;;;code_098a:
;;;			lst      temp2
;;;			ldi      0
;;;			eq?     
;;;			bnt      code_09b2
;;;			pushi    #firstTrue
;;;			pushi    2
;;;			pushi    218
;;;			lofsa    invEvent
;;;			push    
;;;			self     8
;;;			sat      temp0
;;;			bnt      code_09b2
;;;			push    
;;;			pToa     highlightedIcon
;;;			ne?     
;;;			bnt      code_09b2
;;;			pushi    #highlight
;;;			pushi    1
;;;			lst      temp0
;;;			self     6
;;;			jmp      code_0ce0
;;;code_09b2:
;;;			lst      temp2
;;;			ldi      1
;;;			eq?     
;;;			bt       code_09d7
;;;			lst      temp2
;;;			ldi      4
;;;			eq?     
;;;			bnt      code_09ce
;;;			pushi    #message
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			ldi      13
;;;			eq?     
;;;			bt       code_09d7
;;;code_09ce:
;;;			lst      temp2
;;;			ldi      256
;;;			eq?     
;;;			bnt      code_0a64
;;;code_09d7:
;;;			pushi    1
;;;			pTos     highlightedIcon
;;;			callk    IsObject,  2
;;;			bnt      code_0ce0
;;;			pushi    178
;;;			pushi    #view
;;;			pTos     highlightedIcon
;;;			lst      temp2
;;;			ldi      1
;;;			eq?     
;;;			push    
;;;			self     8
;;;			bnt      code_0ce0
;;;			pTos     highlightedIcon
;;;			pToa     okButton
;;;			eq?     
;;;			bnt      code_09fe
;;;			jmp      code_0ceb
;;;			jmp      code_0ce0
;;;code_09fe:
;;;			pTos     highlightedIcon
;;;			pToa     helpIconItem
;;;			eq?     
;;;			bnt      code_0a4d
;;;			pushi    #cursor
;;;			pushi    0
;;;			pToa     highlightedIcon
;;;			send     4
;;;			push    
;;;			ldi      65535
;;;			ne?     
;;;			bnt      code_0a23
;;;			pushi    #setCursor
;;;			pushi    1
;;;			pushi    #cursor
;;;			pushi    0
;;;			pToa     helpIconItem
;;;			send     4
;;;			push    
;;;			lag      gGame
;;;			send     6
;;;code_0a23:
;;;			pTos     state
;;;			ldi      2048
;;;			and     
;;;			bnt      code_0a34
;;;			pushi    #noClickHelp
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0ce0
;;;code_0a34:
;;;			pToa     helpIconItem
;;;			bnt      code_0ce0
;;;			pushi    14
;;;			pushi    #x
;;;			pushi    #signal
;;;			pushi    0
;;;			send     4
;;;			push    
;;;			ldi      16
;;;			or      
;;;			push    
;;;			pToa     helpIconItem
;;;			send     6
;;;			jmp      code_0ce0
;;;code_0a4d:
;;;			pToa     highlightedIcon
;;;			aTop     curIcon
;;;			pushi    #setCursor
;;;			pushi    1
;;;			pushi    #cursor
;;;			pushi    0
;;;			pToa     curIcon
;;;			send     4
;;;			push    
;;;			lag      gGame
;;;			send     6
;;;			jmp      code_0ce0
;;;code_0a64:
;;;			lst      temp2
;;;			ldi      64
;;;			and     
;;;			bnt      code_0b1c
;;;			pushi    #message
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			dup     
;;;			ldi      3
;;;			eq?     
;;;			bnt      code_0a84
;;;			pushi    #advance
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0b18
;;;code_0a84:
;;;			dup     
;;;			ldi      7
;;;			eq?     
;;;			bnt      code_0a93
;;;			pushi    #retreat
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0b18
;;;code_0a93:
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_0ac9
;;;			pToa     highlightedIcon
;;;			bnt      code_0ac0
;;;			pushi    3
;;;			push    
;;;			pushi    #nsTop
;;;			pushi    0
;;;			send     4
;;;			push    
;;;			ldi      1
;;;			sub     
;;;			push    
;;;			pushi    0
;;;			call     localproc_07be,  6
;;;			sat      temp0
;;;			bnt      code_0ac0
;;;			pushi    #highlight
;;;			pushi    2
;;;			lst      temp0
;;;			pushi    1
;;;			self     8
;;;			jmp      code_0b18
;;;code_0ac0:
;;;			pushi    #retreat
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0b18
;;;code_0ac9:
;;;			dup     
;;;			ldi      5
;;;			eq?     
;;;			bnt      code_0b05
;;;			pToa     highlightedIcon
;;;			bnt      code_0afd
;;;			pushi    3
;;;			push    
;;;			pushi    #nsBottom
;;;			pushi    0
;;;			send     4
;;;			push    
;;;			ldi      1
;;;			add     
;;;			push    
;;;			pushi    #bottom
;;;			pushi    0
;;;			pToa     window
;;;			send     4
;;;			push    
;;;			call     localproc_07be,  6
;;;			sat      temp0
;;;			bnt      code_0afd
;;;			pushi    #highlight
;;;			pushi    2
;;;			lst      temp0
;;;			pushi    1
;;;			self     8
;;;			jmp      code_0b18
;;;code_0afd:
;;;			pushi    #advance
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0b18
;;;code_0b05:
;;;			dup     
;;;			ldi      0
;;;			eq?     
;;;			bnt      code_0b18
;;;			lst      temp2
;;;			ldi      4
;;;			and     
;;;			bnt      code_0b18
;;;			pushi    #advanceCurIcon
;;;			pushi    0
;;;			self     4
;;;code_0b18:
;;;			toss    
;;;			jmp      code_0ce0
;;;code_0b1c:
;;;			lst      temp2
;;;			ldi      4
;;;			eq?     
;;;			bnt      code_0b4c
;;;			pushi    #message
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			dup     
;;;			ldi      9
;;;			eq?     
;;;			bnt      code_0b3b
;;;			pushi    #advance
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0b48
;;;code_0b3b:
;;;			dup     
;;;			ldi      3840
;;;			eq?     
;;;			bnt      code_0b48
;;;			pushi    #retreat
;;;			pushi    0
;;;			self     4
;;;code_0b48:
;;;			toss    
;;;			jmp      code_0ce0
;;;code_0b4c:
;;;			lst      temp2
;;;			ldi      16384
;;;			and     
;;;			bnt      code_0ce0
;;;			pushi    #firstTrue
;;;			pushi    2
;;;			pushi    218
;;;			lofsa    invEvent
;;;			push    
;;;			self     8
;;;			sat      temp0
;;;			bnt      code_0ce0
;;;			lst      temp2
;;;			ldi      8192
;;;			and     
;;;			bnt      code_0c0a
;;;			lat      temp0
;;;			bnt      code_0be9
;;;			pushi    #noun
;;;			pushi    0
;;;			send     4
;;;			bnt      code_0be9
;;;			pushi    7
;;;			pushi    0
;;;			pushi    #modNum
;;;			pushi    0
;;;			lat      temp0
;;;			send     4
;;;			push    
;;;			pushi    #noun
;;;			pushi    0
;;;			lat      temp0
;;;			send     4
;;;			push    
;;;			pushi    #helpVerb
;;;			pushi    0
;;;			lat      temp0
;;;			send     4
;;;			push    
;;;			pushi    0
;;;			pushi    1
;;;			lea      @temp9
;;;			push    
;;;			callk    Message,  14
;;;			bnt      code_0be9
;;;			pushi    #respondsTo
;;;			pushi    1
;;;			pushi    236
;;;			lag      gNarratorWin
;;;			send     6
;;;			bnt      code_0bde
;;;			pushi    #eraseOnly
;;;			pushi    0
;;;			lag      gNarratorWin
;;;			send     4
;;;			sat      temp6
;;;			pushi    #eraseOnly
;;;			pushi    1
;;;			pushi    1
;;;			lag      gNarratorWin
;;;			send     6
;;;			pushi    1
;;;			lea      @temp9
;;;			push    
;;;			calle    proc921_0,  2
;;;			pushi    #eraseOnly
;;;			pushi    1
;;;			lst      temp6
;;;			lag      gNarratorWin
;;;			send     6
;;;			jmp      code_0be9
;;;code_0bde:
;;;			pushi    1
;;;			lea      @temp9
;;;			push    
;;;			calle    proc921_0,  2
;;;code_0be9:
;;;			pushi    14
;;;			pushi    #x
;;;			pushi    #signal
;;;			pushi    0
;;;			pToa     helpIconItem
;;;			send     4
;;;			push    
;;;			ldi      65519
;;;			and     
;;;			push    
;;;			pToa     helpIconItem
;;;			send     6
;;;			pushi    #setCursor
;;;			pushi    1
;;;			pushi    999
;;;			lag      gGame
;;;			send     6
;;;			jmp      code_0ce0
;;;code_0c0a:
;;;			lst      temp0
;;;			pToa     okButton
;;;			eq?     
;;;			bnt      code_0c17
;;;			jmp      code_0ceb
;;;			jmp      code_0ce0
;;;code_0c17:
;;;			pushi    #isKindOf
;;;			pushi    1
;;;			class    InvI
;;;			push    
;;;			lat      temp0
;;;			send     6
;;;			not     
;;;			bnt      code_0c77
;;;			pushi    #select
;;;			pushi    2
;;;			lst      temp0
;;;			lat      temp8
;;;			not     
;;;			push    
;;;			self     8
;;;			bnt      code_0ce0
;;;			lat      temp0
;;;			aTop     curIcon
;;;			pushi    #setCursor
;;;			pushi    1
;;;			pushi    #cursor
;;;			pushi    0
;;;			pToa     curIcon
;;;			send     4
;;;			push    
;;;			lag      gGame
;;;			send     6
;;;			lst      temp0
;;;			pToa     helpIconItem
;;;			eq?     
;;;			bnt      code_0ce0
;;;			pTos     state
;;;			ldi      2048
;;;			and     
;;;			bnt      code_0c61
;;;			pushi    #noClickHelp
;;;			pushi    0
;;;			self     4
;;;			jmp      code_0ce0
;;;code_0c61:
;;;			pushi    14
;;;			pushi    #x
;;;			pushi    #signal
;;;			pushi    0
;;;			pToa     helpIconItem
;;;			send     4
;;;			push    
;;;			ldi      16
;;;			or      
;;;			push    
;;;			pToa     helpIconItem
;;;			send     6
;;;			jmp      code_0ce0
;;;code_0c77:
;;;			pToa     curIcon
;;;			bnt      code_0ce0
;;;			pushi    #respondsTo
;;;			pushi    1
;;;			pushi    236
;;;			lag      gNarratorWin
;;;			send     6
;;;			bnt      code_0c9b
;;;			pushi    #eraseOnly
;;;			pushi    0
;;;			lag      gNarratorWin
;;;			send     4
;;;			sat      temp6
;;;			pushi    #eraseOnly
;;;			pushi    1
;;;			pushi    1
;;;			lag      gNarratorWin
;;;			send     6
;;;code_0c9b:
;;;			pushi    #isKindOf
;;;			pushi    1
;;;			class    InvI
;;;			push    
;;;			pToa     curIcon
;;;			send     6
;;;			bnt      code_0cb9
;;;			pushi    #doVerb
;;;			pushi    1
;;;			pushi    #message
;;;			pushi    0
;;;			pToa     curIcon
;;;			send     4
;;;			push    
;;;			lat      temp0
;;;			send     6
;;;			jmp      code_0cca
;;;code_0cb9:
;;;			pushi    #doVerb
;;;			pushi    1
;;;			pushi    #message
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			push    
;;;			lat      temp0
;;;			send     6
;;;code_0cca:
;;;			pushi    #respondsTo
;;;			pushi    1
;;;			pushi    236
;;;			lag      gNarratorWin
;;;			send     6
;;;			bnt      code_0ce0
;;;			pushi    #eraseOnly
;;;			pushi    1
;;;			lst      temp6
;;;			lag      gNarratorWin
;;;			send     6
;;;code_0ce0:
;;;			pushi    #dispose
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			jmp      code_0868
;;;code_0ceb:
;;;			pushi    #dispose
;;;			pushi    0
;;;			lofsa    invEvent
;;;			send     4
;;;			pushi    #hide
;;;			pushi    0
;;;			self     4
;;;			ret     
;;;		)
;;;	)
;;;	
;;;	(method (showSelf param1)
;;;		(gSounds pause:)
;;;		(if
;;;		(and gPseudoMouse (gPseudoMouse respondsTo: #stop))
;;;			(gPseudoMouse stop:)
;;;		)
;;;		(if (gIconBar height?) (gIconBar hide:))
;;;		(if (not window) (= window (SysWindow new:)))
;;;		(if (window window?) (window dispose:) (= window 0))
;;;		(if (not okButton)
;;;			(= okButton (NodeValue (self first:)))
;;;		)
;;;		(= curIcon 0)
;;;		(if (self show: (if argc param1 else gEgo))
;;;			(self doit:)
;;;		)
;;;	)
;;;	
;;;	(method (show param1 &tmp temp0 temp1)
;;;		(gGame
;;;			setCursor: (if curIcon (curIcon cursor?) else (selectIcon cursor?))
;;;		)
;;;		(= temp0 (PicNotValid))
;;;		(PicNotValid 0)
;;;		(= state (| state $0020))
;;;		(if
;;;			(not
;;;				(= temp1
;;;					(self
;;;						drawInvWindow: (if argc param1 else gEgo) (gIconBar curIcon?)
;;;					)
;;;				)
;;;			)
;;;			(= state (& state $ffdf))
;;;		)
;;;		(PicNotValid temp0)
;;;		(return temp1)
;;;	)
;;;	
;;;	(method (hide &tmp temp0)
;;;		(if (& state $0020)
;;;			(gSounds pause: 0)
;;;			(= state (& state $ffdf))
;;;		)
;;;		(if window (window dispose:))
;;;		(if
;;;		(and (IsObject curIcon) (curIcon isKindOf: InvI))
;;;			(if (not (gIconBar curInvIcon?))
;;;				(gIconBar enable: (gIconBar useIconItem?))
;;;			)
;;;			(gIconBar
;;;				curIcon: ((gIconBar useIconItem?)
;;;					cursor: (curIcon cursor?)
;;;					yourself:
;;;				)
;;;				curInvIcon: curIcon
;;;			)
;;;			(if (= temp0 ((gIconBar curIcon?) cursor?))
;;;				(gGame setCursor: temp0)
;;;			)
;;;		)
;;;	)
;;;	
;;;	(method (advance param1 &tmp temp0 temp1 temp2 temp3)
;;;		(= temp1 (if argc param1 else 1))
;;;		(= temp3
;;;			(+ temp1 (= temp2 (self indexOf: highlightedIcon)))
;;;		)
;;;		(repeat
;;;			(= temp0
;;;				(self
;;;					at: (if (<= temp3 size) temp3 else (mod temp3 (- size 1)))
;;;				)
;;;			)
;;;			(if (not (IsObject temp0))
;;;				(= temp0 (NodeValue (self first:)))
;;;			)
;;;			(if (not (& (temp0 signal?) $0004)) (break))
;;;			(++ temp3)
;;;		)
;;;		(self highlight: temp0 1)
;;;	)
;;;	
;;;	(method (retreat param1 &tmp temp0 temp1 temp2 temp3)
;;;		(= temp1 (if argc param1 else 1))
;;;		(= temp3
;;;			(- (= temp2 (self indexOf: highlightedIcon)) temp1)
;;;		)
;;;		(repeat
;;;			(= temp0 (self at: temp3))
;;;			(if (not (IsObject temp0))
;;;				(= temp0 (NodeValue (self last:)))
;;;			)
;;;			(if (not (& (temp0 signal?) $0004)) (break))
;;;			(-- temp3)
;;;		)
;;;		(self highlight: temp0 1)
;;;	)
;;;	
;;;	(method (ownedBy param1)
;;;		(self firstTrue: #ownedBy param1)
;;;	)
;;;	
;;;	(method (drawInvWindow param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 invFirst temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 invWindow [temp22 50])
;;;		(= temp0
;;;			(= temp1 (= temp2 (= temp3 (= temp4 (= temp5 0)))))
;;;		)
;;;		(= invFirst (self first:))
;;;		(while invFirst
;;;			(if
;;;			((= temp9 (NodeValue invFirst)) isKindOf: InvI)
;;;				(if (temp9 ownedBy: param1)
;;;					(temp9 signal: (& (temp9 signal?) $fffb))
;;;					(++ temp0)
;;;					(if
;;;						(>
;;;							(= temp6
;;;								(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;							)
;;;							temp2
;;;						)
;;;						(= temp2 temp6)
;;;					)
;;;					(if
;;;						(>
;;;							(= temp7
;;;								(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;							)
;;;							temp1
;;;						)
;;;						(= temp1 temp7)
;;;					)
;;;				else
;;;					(temp9 signal: (| (temp9 signal?) $0004))
;;;				)
;;;			else
;;;				(++ temp3)
;;;				(= temp5
;;;					(+
;;;						temp5
;;;						(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;					)
;;;				)
;;;				(if
;;;					(>
;;;						(= temp7
;;;							(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;						)
;;;						temp4
;;;					)
;;;					(= temp4 temp7)
;;;				)
;;;			)
;;;			(= invFirst (self next: invFirst))
;;;		)
;;;		(if (not temp0)
;;;			(Print
;;;				addTextF: @temp22 {%s %s} normalHeading empty
;;;				init:
;;;			)
;;;			(return 0)
;;;		)
;;;		(if (> (* (= temp16 (Sqrt temp0)) temp16) temp0)
;;;			(-- temp16)
;;;		)
;;;		(if (> temp16 3) (= temp16 3))
;;;		(if
;;;		(< (* temp16 (= local0 (/ temp0 temp16))) temp0)
;;;			(++ local0)
;;;		)
;;;		(= temp10
;;;			(proc999_3 (+ 4 temp5) (* local0 (+ 4 temp2)))
;;;		)
;;;		(= temp12
;;;			(/ (- 190 (= temp11 (* temp16 (+ 4 temp1)))) 2)
;;;		)
;;;		(= temp13 (/ (- 320 temp10) 2))
;;;		(= temp14 (+ temp12 temp11))
;;;		(= temp15 (+ temp13 temp10))
;;;		(if (= invWindow (self window?))
;;;			(invWindow
;;;				top: temp12
;;;				left: temp13
;;;				right: temp15
;;;				bottom: temp14
;;;				open:
;;;			)
;;;		)
;;;		(= temp20 local0)
;;;		(if temp0
;;;			(= temp18
;;;				(+
;;;					2
;;;					(if (invWindow respondsTo: #yOffset)
;;;						(invWindow yOffset?)
;;;					else
;;;						0
;;;					)
;;;				)
;;;			)
;;;			(= temp19
;;;				(= temp17
;;;					(+
;;;						4
;;;						(if (invWindow respondsTo: #xOffset)
;;;							(invWindow xOffset?)
;;;						else
;;;							0
;;;						)
;;;					)
;;;				)
;;;			)
;;;			(= invFirst (self first:))
;;;			(while invFirst
;;;				(if
;;;					(and
;;;						(not
;;;							(& ((= temp9 (NodeValue invFirst)) signal?) $0004)
;;;						)
;;;						(temp9 isKindOf: InvI)
;;;					)
;;;					(if (not (& (temp9 signal?) $0080))
;;;						(temp9
;;;							nsLeft:
;;;								(+
;;;									temp17
;;;									(/
;;;										(-
;;;											temp2
;;;											(= temp6
;;;												(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;											)
;;;										)
;;;										2
;;;									)
;;;								)
;;;							nsTop:
;;;								(+
;;;									temp18
;;;									(/
;;;										(-
;;;											temp1
;;;											(= temp7
;;;												(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;											)
;;;										)
;;;										2
;;;									)
;;;								)
;;;						)
;;;						(temp9
;;;							nsRight: (+ (temp9 nsLeft?) temp6)
;;;							nsBottom: (+ (temp9 nsTop?) temp7)
;;;						)
;;;						(if (-- temp20)
;;;							(= temp17 (+ temp17 temp2))
;;;						else
;;;							(= temp20 local0)
;;;							(= temp18 (+ temp18 temp1))
;;;							(= temp17 temp19)
;;;						)
;;;					else
;;;						(= temp17 (temp9 nsLeft?))
;;;						(= temp18 (temp9 nsTop?))
;;;					)
;;;					(temp9 show:)
;;;					(if (== temp9 param2) (temp9 highlight:))
;;;				)
;;;				(= invFirst (self next: invFirst))
;;;			)
;;;		)
;;;		(= temp17
;;;			(/
;;;				(- (- (invWindow right?) (invWindow left?)) temp5)
;;;				2
;;;			)
;;;		)
;;;		(= temp11 (- (invWindow bottom?) (invWindow top?)))
;;;		(= temp18 32767)
;;;		(= invFirst (self first:))
;;;		(while invFirst
;;;			(if
;;;			(not ((= temp9 (NodeValue invFirst)) isKindOf: InvI))
;;;				(= temp6
;;;					(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;				)
;;;				(= temp7
;;;					(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
;;;				)
;;;				(if (not (& (temp9 signal?) $0080))
;;;					(if (== temp18 32767) (= temp18 (- temp11 temp7)))
;;;					(temp9
;;;						nsLeft: temp17
;;;						nsTop: temp18
;;;						nsBottom: temp11
;;;						nsRight: (+ temp17 temp6)
;;;					)
;;;				)
;;;				(= temp17 (+ (temp9 nsLeft?) temp6))
;;;				(= temp18 (temp9 nsTop?))
;;;				(temp9 signal: (& (temp9 signal?) $fffb) show:)
;;;			)
;;;			(= invFirst (self next: invFirst))
;;;		)
;;;		(return 1)
;;;	)
;;;)
;;;
;;;(instance invEvent of Event
;;;	(properties)
;;;)
;;;

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
;(use Motion) ;add
;(use Jump) ;add
;(use rm540)
;(use Actor)
;(use Wander)
(use PncMenu)
(use Gun)

(local
	yesI
	selI
	lookI
	pigAppears
	[msgBuf 33]
	[titleBuf 22]
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
;;;		carrying	"You are carrying:" ;ENGLISH
		carrying	"Llevas contigo:" ;SPANISH
			;Title of the inventory display when the object in question
			;has some inventory items.
;;;		empty "You are carrying nothing!" ;ENGLISH
		empty "^No llevas nada!" ;SPANISH
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
						value: obj, 
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
;;;			text: "Select", ;ENGLISH
			text: "Elegir", ;SPANISH
			setSize:,
			moveTo: 
				;(- nsRight (+ MARGIN (yesI nsRight?)))
				(+ nsLeft (+ MARGIN (selI nsLeft?)))
				nsBottom
		)
		(= lookI (DButton new:))
		(lookI 
;;;			text: "Look_", ;English
			text: "Mirar", ;Spanish
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (selI nsRight?)))
				nsBottom
		)
		(= yesI (DButton new:))
		(yesI 
			text: "OK", 
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (lookI nsRight?)))
				nsBottom
		)

		;Add button and resize the dialog.
		(self add: selI lookI yesI, setSize:, center:)

		(return num)
	)

	(method (doit whom &tmp el)
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
				(if (== theCursor 993) 
					(= theCursor 999) ;if select change to walk on exit inv.
				)
				(break)
			)
			
			(if
				(or
					(== el selI)
					(== el lookI)
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
					(998 ;look at item
						;((el value?) showSelf:)
						((el value?) showSelf:) ;display the inventory item normally.
					)
					(101 ;extra ammo clips
						(if (== ((el value?) view?) 100) ;clicked gun with clips
							(load)	
						)
					)
					(else
;;;						(Print {You can't use those items together.}) ;English
						(Print {No puedes usar esos dos objetos juntos.}) ;Spanish
					)
				)
			)
		)
		;Dispose of everything
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


;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)
(use Menu)

(public
	debugRm 0
)

(instance debugRm of Locale
	;(method (handleEvent event &tmp evt obj [temp2 2] node [str 40])
	(method (handleEvent event &tmp n i obj evt [str1 30] [str2 30] [str3 30])
		(if (or (not debugging) (event claimed?)) (return))
		(switch (event type?)
;;;			(mouseDown
;;;				(cond 
;;;					((& (event modifiers?) ctrlDown)
;;;						(event claimed: TRUE)
;;;						(User canControl: TRUE)
;;;						(while (!= mouseUp ((= evt (Event new:)) type?))
;;;							(GlobalToLocal evt)
;;;							(ego posn: (evt x?) (evt y?) setMotion: 0)
;;;							(RedrawCast)
;;;							(evt dispose:)
;;;						)
;;;						(evt dispose:)
;;;					)
;;;					((& (event modifiers?) shiftDown)
;;;						(event claimed: TRUE)
;;;						(= obj
;;;							(Print
;;;								(Format @str 801 0 (event x?) (event y?))
;;;								#at 150 100
;;;								#font 999
;;;								#dispose
;;;							)
;;;						)
;;;						(while (!= mouseUp ((= evt (Event new:)) type?))
;;;							(evt dispose:)
;;;						)
;;;						(obj dispose:)
;;;						(evt dispose:)
;;;					)
;;;				)
;;;			)
			(mouseDown
				(cond 
					((& (event modifiers?) shiftDown)
						(event claimed: TRUE)
						(= obj
							(Print
								(Format @str1 20 0 (event x?) (event y?))
								#at 150 100
								#font 999
								#dispose
							)
						)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(obj dispose:)
						(evt dispose:)
					)
					((& (event modifiers?) ctrlDown)
						(event claimed: TRUE)
						(User canControl: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(GlobalToLocal evt)
							(ego posn: (evt x?) (evt y?) setMotion: 0)
							(Animate (cast elements?) FALSE)
							(evt dispose:)
						)
						(evt dispose:)
					)
				)
			)


;;;			(keyDown
;;;				(event claimed: TRUE)
;;;				(switch (event message?)
;;;					(`?
;;;						(Print 801 1)
;;;					)
;;;					(`@s
;;;						(= node (cast first:))
;;;						(while node
;;;							(= obj (NodeValue node))
;;;							(Print
;;;								(Format @str 801 2
;;;									(obj view?)
;;;									(obj x?)
;;;									(obj y?)
;;;									(if (& (obj signal?) notUpd) {stopUpd:\n} else {})
;;;									(if (& (obj signal?) ignrAct) {ignoreActors:\n} else {})
;;;									(if
;;;										(or
;;;											(== (obj superClass?) Actor)
;;;											(== (obj superClass?) Ego)
;;;										)
;;;										(obj illegalBits?)
;;;									else
;;;										-1
;;;									)
;;;								)
;;;								#title (obj name?)
;;;								#icon (obj view?) (obj loop?) (obj cel?)
;;;							)
;;;							(= node (cast next: node))
;;;						)
;;;					)
;;;					(`@e
;;;						(Format @str 801 3
;;;							(ego x?)
;;;							(ego y?)
;;;							(ego loop?)
;;;							(ego cel?)
;;;						)
;;;						(Print @str
;;;							#icon (ego view?) #loop 0 #cel 0
;;;						)
;;;					)
;;;					(`@i
;;;						(= node (GetNumber {ID number of the object?}))
;;;						((inventory at: node) moveTo: ego)
;;;						(if (== node 1)
;;;							(= [numAmmoClips 1] 7)
;;;							(= [numAmmoClips 2] 7)
;;;						)
;;;					)
;;;					(`@t
;;;						(curRoom newRoom: (GetNumber {Which room number?}))
;;;					)
;;;					(`@v
;;;						(Show VMAP)
;;;					)
;;;					(`@p
;;;						(Show PMAP)
;;;					)
;;;					(`@c
;;;						(Show CMAP)
;;;					)
;;;					(`@m
;;;						(theGame showMem:)
;;;					)
;;;					(else
;;;						(event claimed: FALSE)
;;;					)
;;;				)
				
				
			(keyDown
				(switch (event message?)
					(`@c
						(Show CMAP)
						(Animate (cast elements?) FALSE)
						(while (== nullEvt ((= event (Event new:)) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
						(return)
					)
					(`@d
						(SetDebug)
					)
					(`@e
						(if (Load VIEW (= n (GetNumber {New Ego View:})))
							(= currentEgoView n)
							(NormalEgo)
						)
					)
					(`@f
						(^= debugOn TRUE)
						(Print
							(Format @str1 20 1 (if debugOn {_} else { NOT_}))
						)
					)
;;;					(`@g
;;;						(if (Load PICTURE 999)
;;;							(DrawPic 999 VSHUTTER TRUE)
;;;						else
;;;							(SetDebug)
;;;						)
;;;					)
					(`@i
						(User canInput: TRUE)
					)
					(`@m
						(theGame showMem:) ;fix barra retroceso
					)
					(`@p
						(Show PMAP)
					)
					(`@r
						(Print (Format @str1 20 2 curRoomNum))
					)
;;;					(`@s
;;;						(if playingAsPatti
;;;							(= playingAsPatti FALSE)
;;;							(= currentEgoView 700)
;;;							(= currentEgo (Format @egoName 20 3))
;;;						else
;;;							(= playingAsPatti TRUE)
;;;							(= currentEgoView 800)
;;;							(= currentEgo (Format @egoName 20 4))
;;;						)
;;;						(NormalEgo)
;;;					)
;;;					(`@t
;;;						(Print
;;;							(Format @str1 20 5
;;;								(/ orchidSeconds 600)
;;;								(/ (mod orchidSeconds 600) 10)
;;;							)
;;;						)
;;;					)
					(`@v
						(Show VMAP)
					)
;;;					(`@w
;;;						(= str1 0)
;;;						(if
;;;						(!= -1 (GetInput @str1 50 {Writing to "ego.log"}))
;;;							(Format @str2 20 6 curRoomNum)
;;;							(Format @str3 20 7
;;;								(ego view?)
;;;								(ego loop?)
;;;								(ego cel?)
;;;								(ego x?)
;;;								(ego y?)
;;;								(ego priority?)
;;;							)
;;;							(File
;;;								name: {ego.log}
;;;								write: @str2 @str1 {]_} @str3 {\0D\n}
;;;								close:
;;;							)
;;;						)
;;;					)
;;;					(`@x
;;;						(= quit TRUE) ;fix not exit in room140 control alt x
;;;					)
;;;					(`@z
;;;						(= quit TRUE)
;;;					)
					;the next few use the Ctrl modifier
					(`^t
						(= n (GetNumber {Teleport to}))
						(if (Load SCRIPT n)
							(NormalEgo)
							(curRoom newRoom: n)
						else
							(Print 20 8)
							(SetDebug)
						)
					)
					(`^d
						(if programControl
							(= programControl FALSE)
							(TheMenuBar draw:)
							(StatusLine enable:)
							(NormalEgo)
						else
							(= programControl TRUE)
							(= n (GetNumber {Teleport to}))
							(if (Load SCRIPT n)
								(curRoom newRoom: n)
							else
								(SetDebug)
							)
						)
					)
					(`^e
						(Print
							(Format
								@str1
								{view %d loop %d cel %d posn %d %d pri %d OnControl $%x Origin on $%x}
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego priority?)
								(ego onControl:)
								(ego onControl: origin)
							)
							#icon (ego view?) (ego loop?) (ego cel?)
						)
					)
					(BACKSPACE
						(theGame showMem:) ;fix barra retroceso
					)
				)
			)				
				
				
				
				
				
				
				
				
				
			)
		
		)
	)




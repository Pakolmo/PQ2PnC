;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)
(use rm4)

(public
	rm260 0
)
(local
	marieLetter

	closedDrawer
	wallet
)
(instance rm260 of Room
	(properties
		picture 11
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(curRoom newRoom: 10)
		
	)
)

(instance drawerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #hide)
				(curRoom drawPic: 12)
				(if (InRoom iThankYouLetter 12)
					((= marieLetter (View new:))
						view: 59
						loop: 0
						cel: 2
						posn: 146 115
						init:
						ignoreActors:
						stopUpd:
					)
				)
				(if (InRoom iWallet 12)
					((= wallet (View new:))
						view: 59
						loop: 0
						cel: 0
						posn: 190 105
						init:
						ignoreActors:
						stopUpd:
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'get,remove/letter,card')
						(if (InRoom iThankYouLetter 12)
							(marieLetter dispose:)
							(Print 4 83 #draw)
							(ego get: iThankYouLetter)
							(SolvePuzzle 1 106)
						else
							(Print 4 97)
						)
					)
					((Said 'get,remove/note')
						(if (InRoom iThankYouLetter 12)
							(Print 4 98)
						else
							(Print 4 97)
						)
					)
					((Said 'get/billfold')
						(if (InRoom iWallet 12)
							(wallet dispose:)
							(Print 4 83 #draw)
							(ego get: iWallet)
							(SolvePuzzle 1 105)
						else
							(Print 4 97)
						)
					)
					((Said 'deposit,replace/letter,card')
						(if (ego has: iThankYouLetter)
							((= marieLetter (View new:))
								view: 59
								posn: 146 115
								loop: 0
								cel: 2
								init:
								ignoreActors:
								stopUpd:
							)
							(ego put: iThankYouLetter 12)
						else
							(Print 4 99)
						)
					)
					((Said 'deposit,replace/billfold')
						(if (ego has: iWallet)
							((= wallet (View new:))
								view: 59
								posn: 190 105
								loop: 0
								cel: 0
								init:
								stopUpd:
							)
							(ego put: 7 12)
						else
							(DontHave)
						)
					)
					((Said 'look,frisk[/drawer]')
						(inventory
							carrying: {Your desk drawer contains:}
							empty: {Your desk drawer is empty.}
							showSelf: 12
						)
					)
					(
						(or
							(Said 'look,read/*<ya<thank')
							(Said 'look,read/letter')
						)
						(if (InRoom iThankYouLetter 12)
							(Print 4 100)
						else
							(event claimed: 0)
						)
					)
					(
						(or
							(Said '/shot<mug')
							(Said '/mugshot')
						)
							(Print 4 27)
						)
					((Said 'close[/drawer]')
						(curRoom drawPic: (curRoom picture?))
						(cast eachElementDo: #dispose)
						(cast eachElementDo: #delete)
						(ego init:)
						(= closedDrawer 1)
						(rm4 setScript: rm4Script)
						(rm4Script changeState: 3)
					)
				)
			)
		)
	)
)

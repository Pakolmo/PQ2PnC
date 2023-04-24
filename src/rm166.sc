;;; Sierra Script 1.0 - (do not remove this comment)
(script# 166)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)
(use PncMenu)

(public
	rm166 0
)

(instance locCove of Prop
	(properties
		x 290
		y 160
	)
)

(instance locArnie of Prop
	(properties
		x 80
		y 60
	)
)

(local

)


(instance rm166 of Room
	(properties
		picture  166
		style IRISIN
	)
	
	(method (init)
		(curRoom setRegions: 950)	
		(Load VIEW 166)
		(super init:)
		(= gunFireState gunUSELESS)
		(User canInput: TRUE canControl: TRUE)
		(locCove
			view: 166
			loop: 0
			cel: 0
			init:
		)
		(locArnie
			view: 166
			loop: 0
			cel: 1
			init:
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?)
			(return)
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
			(if (ClickedOnObj locCove (event x?) (event y?)) ;cove
				(event claimed: TRUE)
				(switch theCursor
					(998 ;look
						(Print {Cotton Cove})
					)
					(995 ;hand
						(= driveDest 61)
						(theGame newRoom: 13)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(if (ClickedOnObj locArnie (event x?) (event y?)) ;cove
				(event claimed: TRUE)
				(switch theCursor
					(998 ;look
						(Print {Arnie's Resturant})
					)
					(995 ;hand
						(= driveDest 29)
						(theGame newRoom: 13)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)

(instance trunkScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				
			)
		)
	)
)

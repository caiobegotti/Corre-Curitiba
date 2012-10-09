//
//  CCMasterViewController.h
//  CorraCuritiba
//
//  Created by cits on 04/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <UIKit/UIKit.h>

@class CCDetailViewController;

@interface CCMasterViewController : UITableViewController {
	/*
	UIActivityIndicatorView *activity;
	*/
	NSMutableArray *calendar;
	NSMutableData *responseData;
}

@property (strong, nonatomic) CCDetailViewController *detailViewController;
@property (nonatomic, retain) NSMutableArray *resultArray;

@end

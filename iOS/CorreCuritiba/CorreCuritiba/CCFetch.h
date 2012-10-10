//
//  CCFetch.h
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CCFetch : NSObject
{
	NSMutableData *responseData;
}

- (void)populateCalendar;

@end

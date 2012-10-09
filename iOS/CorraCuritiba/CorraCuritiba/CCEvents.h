//
//  CCEvents.h
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CCEvents : NSObject {
    NSMutableArray *events;
	NSDictionary *receivedDict;
}

@property (nonatomic, retain) NSMutableArray *events;

-(id) init;
-(NSMutableArray*) getEvents;
-(void) setEvents:(NSDictionary *)venues;

+(CCEvents*) sharedEvents;

@end

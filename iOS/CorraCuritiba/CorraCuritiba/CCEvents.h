//
//  CCEvents.h
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CCEvents : NSObject
{
    NSMutableArray *events;
}

@property (nonatomic, retain) NSMutableArray *events;

-(id)init;
-(NSMutableArray*)getEvents;
-(void)setEvents:(NSDictionary *)venues;

// Singleton so that we can get events from anywhere in the app
+(CCEvents*)sharedEvents;

@end

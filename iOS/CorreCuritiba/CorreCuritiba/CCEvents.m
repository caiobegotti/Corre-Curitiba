//
//  CCEvents.m
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCEvents.h"

#import "CCMasterViewController.h"

@implementation CCEvents

@synthesize events;

static CCEvents* _sharedEvents = nil;

+(CCEvents*)sharedEvents
{
	@synchronized([CCEvents class]) {
		if (!_sharedEvents) {
			[[self alloc] init];
		}
		return _sharedEvents;
	}
	return nil;
}

+(id)alloc
{
	@synchronized([CCEvents class]) {
		NSAssert(_sharedEvents == nil, @"Tentativa de alocar segunda instância do singleton");
		_sharedEvents = [super alloc];
		return _sharedEvents;
	}
}

-(id)init
{
	self = [super init];
	if (self != nil) {
		NSLog(@"Init not nil");
		events = [[NSMutableArray alloc] init];
	}
	return self;
}

-(NSMutableArray*)getEvents
{
    return events;
}

-(void)setEvents:(NSDictionary *)venues
{
	for (NSDictionary *list in venues)
	{
		NSDictionary *entry = [venues objectForKey:list];
		[events addObject:entry];
	}
	NSNotification *notify = [NSNotification notificationWithName:@"reloadRequest" object:self];
	[[NSNotificationCenter defaultCenter] postNotification:notify];
}

@end

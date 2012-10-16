//
//  CCEvents.m
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCData.h"

#import "CCMasterViewController.h"

@implementation CCData

@synthesize data;

static CCData* _sharedData = nil;

+(CCData*)sharedData
{
	@synchronized([CCData class]) {
		if (!_sharedData) {
			[[self alloc] init];
		}
		return _sharedData;
	}
	return nil;
}

+(id)alloc
{
	@synchronized([CCData class]) {
		NSAssert(_sharedData == nil, @"Tentativa de alocar segunda instância do singleton");
		_sharedData = [super alloc];
		return _sharedData;
	}
}

-(id)init
{
	self = [super init];
	if (self != nil) {
		NSLog(@"Init not nil");
		data = [[NSMutableArray alloc] init];
	}
	return self;
}

-(void)populateData:(NSDictionary *)json
{
	for (NSDictionary *list in json)
	{
		NSDictionary *entry = [json objectForKey:list];
		[data addObject:entry];
	}
	NSNotification *notify = [NSNotification notificationWithName:@"reloadRequest" object:self];
	[[NSNotificationCenter defaultCenter] postNotification:notify];
}

-(NSMutableArray*)getData
{
    NSSortDescriptor *sortDesc = [[NSSortDescriptor alloc] initWithKey:@"Data:" ascending:YES];
    [data sortUsingDescriptors:[NSArray arrayWithObject:sortDesc]];
    return data;
}

@end


@implementation CCDataSections

-(NSArray *)getDataSections {
    NSArray *data = [[CCData sharedData] getData];
    NSMutableArray *sections = [[NSMutableArray alloc] init];
    
    for (NSDictionary *entry in data) {
        [sections addObject:[entry objectForKey:@"Data:"]];
    }
    
    
    NSMutableArray *unique = [NSMutableArray array];
    
    for (id obj in sections) {
        if (![unique containsObject:obj]) {
            [unique addObject:obj];
        }
    }

    return unique;
}

@end
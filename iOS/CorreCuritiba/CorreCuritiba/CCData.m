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
			_sharedData = [[self alloc] init];
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
		data = [[NSMutableDictionary alloc] init];
	}
	return self;
}

-(void)populateData:(NSDictionary *)json
{
	for (NSDictionary *list in json)
	{
		NSDictionary *entry = [json objectForKey:list];
        NSString *key = [entry objectForKey:@"Data:"];
        
        NSMutableArray *array = [data objectForKey:key];
        if (array == nil) {
            array = [[NSMutableArray alloc] init];
            [data setObject:array forKey:key];
        }
        
        [array addObject:entry];
	}
	NSNotification *notify = [NSNotification notificationWithName:@"reloadRequest" object:self];
	[[NSNotificationCenter defaultCenter] postNotification:notify];
}

-(NSMutableArray*)getDataForSection:(NSObject*)section;
{
    NSMutableArray *array = [data objectForKey:section];
    
    NSSortDescriptor *sortDesc = [[NSSortDescriptor alloc] initWithKey:@"Data:" ascending:YES];
    [array sortUsingDescriptors:[NSArray arrayWithObject:sortDesc]];
    return array;
}

-(NSMutableDictionary*)getData;
{
    return data;
}

@end


@implementation CCDataSections

-(NSArray *)getDataSections {
    NSDictionary *data = [[CCData sharedData] getData];
    
    return [data allKeys];
}

@end
//
//  CCEvents.h
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CCData : NSObject
{
    NSMutableDictionary *data;
}

@property (nonatomic, retain) NSMutableDictionary *data;

-(id)init;
-(void)populateData:(NSDictionary *)json;
-(NSMutableArray*)getDataForSection:(NSObject*)section;
-(NSMutableDictionary*)getData;

// Singleton so that we can get events from anywhere in the app
+(CCData*)sharedData;

@end

@interface CCDataSections : NSObject

-(NSArray *)getDataSections;

@end
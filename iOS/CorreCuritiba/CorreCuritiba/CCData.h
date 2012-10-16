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
    NSMutableArray *data;
}

@property (nonatomic, retain) NSMutableArray *data;

-(id)init;
-(NSMutableArray*)getData;
-(void)populateData:(NSDictionary *)json;

// Singleton so that we can get events from anywhere in the app
+(CCData*)sharedData;

@end

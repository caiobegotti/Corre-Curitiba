//
//  CCEvent.h
//  CorreCuritiba
//
//  Created by cits on 16/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CCEvent : NSObject {
    NSString *local;
    NSString *name;
    NSString *date;
    NSString *enrollment;
    NSString *applications;
    NSString *distance;
    NSString *description;
    NSString *link;
}

@property (nonatomic, retain) NSString *local;
@property (nonatomic, retain) NSString *name;
@property (nonatomic, retain) NSString *date;
@property (nonatomic, retain) NSString *enrollment;
@property (nonatomic, retain) NSString *applications;
@property (nonatomic, retain) NSString *distance;
@property (nonatomic, retain) NSString *description;
@property (nonatomic, retain) NSString *link;

-(id)initWithParam:(NSIndexPath *)indexPath;

@end

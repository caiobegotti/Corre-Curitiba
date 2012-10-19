//
//  CCMenuCell.m
//  CorreCuritiba
//
//  Created by cits on 19/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCMenuCell.h"

@implementation CCMenuCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void) layoutSubviews {
    [super layoutSubviews];
    self.cellTitle.frame = CGRectMake(40, 10, self.cellTitle.frame.size.width, self.cellTitle.frame.size.height);
    self.cellDetailTittle.frame = CGRectMake(40, 40, self.cellDetailTittle.frame.size.width, self.cellDetailTittle.frame.size.height);
}

@end

//
//  CCDetailViewController.h
//  CorreCuritiba
//
//  Created by cits on 10/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CCDetailViewController : UIViewController <UISplitViewControllerDelegate>

@property (strong, nonatomic) id detailItem;

@property (weak, nonatomic) IBOutlet UILabel *detailTitle;

@property (weak, nonatomic) IBOutlet UILabel *detailLocal;
@property (weak, nonatomic) IBOutlet UILabel *detailName;
@property (weak, nonatomic) IBOutlet UILabel *detailDate;
@property (weak, nonatomic) IBOutlet UILabel *detailEnrollment;
@property (weak, nonatomic) IBOutlet UILabel *detailApplications;
@property (weak, nonatomic) IBOutlet UILabel *detailDistance;
@property (weak, nonatomic) IBOutlet UILabel *detailDescription;
@property (weak, nonatomic) IBOutlet UILabel *detailLink;

@end

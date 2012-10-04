//
//  CCDetailViewController.h
//  CorraCuritiba
//
//  Created by cits on 04/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CCDetailViewController : UIViewController <UISplitViewControllerDelegate>

@property (strong, nonatomic) id detailItem;

@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@end

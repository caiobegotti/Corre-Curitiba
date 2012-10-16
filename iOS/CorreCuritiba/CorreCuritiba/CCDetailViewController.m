//
//  CCDetailViewController.m
//  CorreCuritiba
//
//  Created by cits on 10/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCDetailViewController.h"

#import "CCEvent.h"

@interface CCDetailViewController ()
@property (strong, nonatomic) UIPopoverController *masterPopoverController;
- (void)configureView;
@end

@implementation CCDetailViewController

#pragma mark - Managing the detail item

- (void)setDetailItem:(id)newDetailItem
{
    //NSLog(@"Debug... %@",newDetailItem);
    if (_detailItem != newDetailItem) {
        _detailItem = newDetailItem;
        
        // Update the view.
        [self configureView];
    }

    if (self.masterPopoverController != nil) {
        [self.masterPopoverController dismissPopoverAnimated:YES];
    }        
}

- (void)configureView
{
    // Update the user interface for the detail item.
    
    if (self.detailItem) {
        self.detailTitle.text = [self.detailItem name];
        
        self.detailLocal.text = [self.detailItem local];
        self.detailName.text = [self.detailItem name];
        self.detailDate.text = [self.detailItem date];
        self.detailEnrollment.text = [self.detailItem enrollment];
        self.detailApplications.text = [self.detailItem applications];
        self.detailDistance.text = [self.detailItem distance];
        self.detailDescription.text = [self.detailItem description];
        self.detailLink.text = [self.detailItem link];
    }
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.

    [self configureView];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Split view

- (void)splitViewController:(UISplitViewController *)splitController willHideViewController:(UIViewController *)viewController withBarButtonItem:(UIBarButtonItem *)barButtonItem forPopoverController:(UIPopoverController *)popoverController
{
    barButtonItem.title = NSLocalizedString(@"Master", @"Master");
    [self.navigationItem setLeftBarButtonItem:barButtonItem animated:YES];
    self.masterPopoverController = popoverController;
}

- (void)splitViewController:(UISplitViewController *)splitController willShowViewController:(UIViewController *)viewController invalidatingBarButtonItem:(UIBarButtonItem *)barButtonItem
{
    // Called when the view is shown again in the split view, invalidating the button and popover controller.
    [self.navigationItem setLeftBarButtonItem:nil animated:YES];
    self.masterPopoverController = nil;
}

@end

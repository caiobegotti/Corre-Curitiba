//
//  CCMasterViewController.m
//  CorreCuritiba
//
//  Created by cits on 10/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCMasterViewController.h"

#import "CCDetailViewController.h"

#import "CCFetch.h"

#import "CCData.h"

#import "CCEvent.h"

#import "CCMenuCell.h"

@interface CCMasterViewController () {
    NSMutableArray *_objects;
}
@end

@implementation CCMasterViewController

- (void)awakeFromNib
{
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
        self.clearsSelectionOnViewWillAppear = NO;
        self.contentSizeForViewInPopover = CGSizeMake(320.0, 600.0);
    }
    [super awakeFromNib];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	self.detailViewController = (CCDetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
    self.tableView.separatorColor = [UIColor clearColor];
    self.navigationController.navigationBar.tintColor = [UIColor blackColor];
    
    // Visual cue that there are stuff still being loaded
	activity = [[UIActivityIndicatorView alloc] initWithFrame:CGRectMake(0.0f, 0.0f, 32.0f, 32.0f)];
    [activity setCenter:CGPointMake(160.0f, 208.0f)];
    [activity setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleGray];
    [self.tableView addSubview:activity];
	[self.view setUserInteractionEnabled: NO];
	[activity startAnimating];
	
	// Dispatch the JSON downloader and parser
	// to populate NSMutableArray *events from CCEvents
	CCFetch *fetch = [CCFetch new];
	[fetch populateCalendar];
	
	// Registers a reloader for the tableView data
	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(forceReload) name:@"reloadRequest" object:nil];
}

- (void)viewDidUnload
{
	[[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)insertNewObject:(id)sender
{
    if (!_objects) {
        _objects = [[NSMutableArray alloc] init];
    }
    [_objects insertObject:[NSDate date] atIndex:0];
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    [self.tableView insertRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
}

#pragma mark - Table View

- (void)forceReload
{
	[self.tableView reloadData];
    
    // Visual cue that we're done loading web data and ready to make them visible in cells
	[self.view setUserInteractionEnabled: YES];
	[activity stopAnimating];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    NSArray *set = [CCDataSections getDataSections];
    return [set count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section
{
    NSArray *set = [CCDataSections getDataSections];
    return [set objectAtIndex:section];
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 30;
}
- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    UIView *headerView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, 30)];
    UILabel *headerLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, headerView.frame.size.width, headerView.frame.size.height)];
    
    NSArray *set = [CCDataSections getDataSections];

    headerLabel.textAlignment = NSTextAlignmentCenter;
    headerLabel.backgroundColor = [UIColor blackColor];
    headerLabel.textColor = [UIColor whiteColor];
    headerLabel.font = [UIFont boldSystemFontOfSize:22];
    headerLabel.text = [set objectAtIndex:section];
    
    headerView.backgroundColor = [UIColor blackColor];
    [headerView addSubview:headerLabel];
    
    return headerView;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSArray *sections = [CCDataSections getDataSections];
    NSArray *data = [[CCData sharedData] getDataForSection:[sections objectAtIndex:section]];

    return [data count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"MenuCellView";
    CCMenuCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
        NSArray *topLevelObjects = [[NSBundle mainBundle] loadNibNamed:cellIdentifier owner:self options:nil];
        cell = [topLevelObjects objectAtIndex:0];
    }
    
    CCEvent *event = [[CCEvent alloc] initWithSection:[indexPath section] andRow:[indexPath row]];

	cell.cellDetailTittle.text = event.distance;
    cell.cellTitle.text = event.name;
    
    CGRect barFrame = CGRectMake(0, 0, 20, cell.frame.size.height);
    UILabel *barLabel = [[UILabel alloc] initWithFrame:barFrame];
    barLabel.backgroundColor = [UIColor clearColor];
    
    [cell addSubview:barLabel];
        
    NSScanner *scanner = [NSScanner scannerWithString:event.distance];
    NSMutableString *distance = [NSMutableString stringWithCapacity:event.distance.length];
    NSCharacterSet *digits = [NSCharacterSet characterSetWithCharactersInString:@"0123456789"];
    
    while ([scanner isAtEnd] == NO) {
        NSString *buffer;
        if ([scanner scanCharactersFromSet:digits intoString:&buffer]) {
            [distance appendString:buffer];
            
        } else {
            [scanner setScanLocation:([scanner scanLocation] + 1)];
        }
    }
    
    int fig = [distance intValue];
    if (fig <= 5000) {
        // 5K are easy
        barLabel.backgroundColor = [UIColor colorWithRed:125/255.0f green:158/255.0f blue:192/255.0f alpha:1];
    } else if (fig <= 10000) {
        // 10K are okay
        barLabel.backgroundColor = [UIColor colorWithRed:227/255.0f green:207/255.0f blue:87/255.0f alpha:1];
    } else if (fig <= 22000) {
        // Half-marathons are hard
        barLabel.backgroundColor = [UIColor colorWithRed:255/255.0f green:153/255.0f blue:18/255.0f alpha:1];
    } else if (fig <= 43000) {
        // Marathons are painful
        barLabel.backgroundColor = [UIColor colorWithRed:255/255.0f green:114/255.0f blue:86/255.0f alpha:1];
    } else if (fig <= 100000) {
        // You better know what you're doing
        barLabel.backgroundColor = [UIColor colorWithRed:142/255.0f green:56/255.0f blue:142/255.0f alpha:1];
    } else {
        // Just to keep the padding of the bar
        barLabel.backgroundColor = [UIColor colorWithRed:193/255.0f green:193/255.0f blue:193/255.0f alpha:1];
    }
    
    return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    return NO;
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        [_objects removeObjectAtIndex:indexPath.row];
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
		NSMutableDictionary *data = [[CCData sharedData] getData];
        NSString *section = [[data allKeys] objectAtIndex:[indexPath section]];
		NSMutableDictionary *selected = [[[CCData sharedData] getDataForSection:section] objectAtIndex:[indexPath row]];
        
        self.detailViewController.detailItem = selected;
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier] isEqualToString:@"showDetail"]) {
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        CCEvent *event = [[CCEvent alloc] initWithSection:[indexPath section] andRow:[indexPath row]];
        [[segue destinationViewController] setDetailItem:event];
    }
}

@end

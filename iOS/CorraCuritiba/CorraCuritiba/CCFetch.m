//
//  CCFetch.m
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCFetch.h"

#import "CCEvents.h"

#import "SBJson/SBJson.h"

@implementation CCFetch

#pragma mark - Json
 
- (void)populateCalendar {
	responseData = [NSMutableData data];
	NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:@"http://cc.ueberalles.net/"]];
	[[NSURLConnection alloc] initWithRequest:request delegate:self];
}

- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response {
	[responseData setLength:0];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data {
	[responseData appendData:data];
}

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error {
	NSLog(@"Calendário em manutenção, tente novamente mais tarde!");
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection {
	NSString *responseString = [[NSString alloc] initWithData:responseData encoding:NSUTF8StringEncoding];
	NSError *error;
	SBJsonParser *json = [SBJsonParser new];
	NSDictionary *venues = [json objectWithString:responseString error:&error];

	if (venues == nil) {
		NSLog(@"Calendário não foi carregado corretamente!");
		NSLog(@"Erro: %@", error);
	} else {				
		[[CCEvents sharedEvents] setEvents:venues];
	}
}

@end

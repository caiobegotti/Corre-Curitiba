#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# this file is under public domain <caio1982@gmail.com>

# dreamhost stuff below
#from __future__ import with_statement

#import sys
#sys.path.append('/home/caio/py_libs/')

import codecs
import json
import re

# should match all distances in kilometers
regex = re.compile("\d+[,.]?\d?[ ]?km", re.IGNORECASE)

from lxml import etree
from datetime import date

parser = etree.HTMLParser()
stamp = date.today()

def venues():
    domain = 'http://www.trainerassessoria.com.br/'
    url = '%s/calendario-de-eventos/%s/%s' % (domain, stamp.year, stamp.month)
    tree = etree.parse(url, parser)
    venues = [domain + i for i in tree.xpath('//dd[@class="linha_calend"]/a//@href')]
    return venues

def calendar():
    calendar = {}
    for v in venues():
        tree = etree.parse(v, parser)
        keys = tree.xpath('//dt//text()')
        values = tree.xpath('//dd//text()')
        data = zip(keys, values)
        info = {}
        for key, value in data:
            info[key] = value
        calendar[v] = info
    return calendar

data = calendar()

filename = '%s.%s.json' % (stamp.year, stamp.month)
with codecs.open(filename, 'w', 'utf-8') as output:
    output.write(json.dumps(data, indent=4))
    output.close()

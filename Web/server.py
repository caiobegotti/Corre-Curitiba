#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# this file is under public domain <caio1982@gmail.com>

import json
import web

urls = (
    '/', 'index'
)

app = web.application(urls, globals(), True)

class index():
    def GET(self):
        web.header('content-type', 'application/json')
        with open('2012.10.json', 'r') as data:
            output = json.dumps(json.load(data), indent=4)
            data.close()
            return output

#web.wsgi.runwsgi = lambda func, addr=None: web.wsgi.runfcgi(func, addr)

if __name__ == "__main__":
    #web.runwsgi = web.runfcgi
    app.run()

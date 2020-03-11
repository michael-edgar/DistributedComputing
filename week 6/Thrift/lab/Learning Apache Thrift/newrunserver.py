
 #!/usr/bin/env python
 
import os
import http.server
import http.server
 

class Handler(http.server.CGIHTTPRequestHandler):
   cgi_directories  = ['/']
 

print("Starting server on port 8082...")
 

http.server.HTTPServer(('', 8082), Handler).serve_forever()

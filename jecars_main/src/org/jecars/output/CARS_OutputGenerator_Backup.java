/*
 * Copyright 2008 NLR - National Aerospace Laboratory
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jecars.output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import nl.msd.jdots.JD_Taglist;
import org.jecars.CARS_ActionContext;
import org.jecars.CARS_Utils;
import org.jecars.backup.JB_ExportData;
import org.jecars.backup.JB_Options;
import org.jecars.backup.JB_Session;

/**
 * CARS_OutputGenerator_Properties
 * 
 * A properties output generator
 *
 * @version $Id: CARS_OutputGenerator_Backup.java,v 1.1 2008/10/21 10:13:46 weertj Exp $
 */
public class CARS_OutputGenerator_Backup extends CARS_DefaultOutputGenerator implements CARS_OutputGenerator {
   
  static final public String LF = "\n";
    
  /** Creates a new instance of CARS_OutputGenerator_Backup
   */
  public CARS_OutputGenerator_Backup() {
  }
  
  /** Create the header of the message
   * @param pContext The action context
   * @param pMessage the string builder in which the header must be added
   */
  @Override
  public void createHeader( CARS_ActionContext pContext, StringBuilder pMessage ) {
    pContext.setContentType( "text/plain" );
    return;
  }

  /** Create the footer of the message
   * @param pContext The action context
   * @param pMessage the string builder in which the header must be added
   */
  @Override   
  public void createFooter( CARS_ActionContext pContext, StringBuilder pMessage ) {
    return;
  }
 
  /** addThisNodeEntry
   * 
   * @param pContext
   * @param pMessage
   * @param pThisNode
   * @param pFromNode
   * @param pToNode
   * @throws javax.jcr.RepositoryException
   * @throws java.lang.Exception
   */
  @Override
  public void addThisNodeEntry( CARS_ActionContext pContext, StringBuilder pMessage, Node pThisNode,
                                long pFromNode, long pToNode ) throws RepositoryException, Exception {    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();      
    JB_ExportData export = new JB_ExportData();
    JB_Options options = new JB_Options();
    export.exportToStream( pThisNode, baos, options );
    baos.close();
    pMessage.append( baos.toString() );    
    return;
  }
  
  /** Is this output a RSS/Atom feed type
   * @return true for yes
   */   
  @Override
  public boolean isFeed() {
    return false;
  }

  
}

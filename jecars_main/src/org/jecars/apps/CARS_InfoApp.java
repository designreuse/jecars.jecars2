/*
 * Copyright 2010 NLR - National Aerospace Laboratory
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
package org.jecars.apps;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.jecars.CARS_Factory;
import org.jecars.CARS_Main;

/**
 * CARS_InfoApp

 */
public class CARS_InfoApp extends CARS_DefaultInterface {

  /** Creates a new instance of CARS_InfoApp
   */
  public CARS_InfoApp() {
    super();
  }
 
  
  /** getVersion
   * 
   * @return
   */
  @Override
  public String getVersion() {
    return getClass().getName() + ": " + CARS_Main.PRODUCTNAME + " version=" + CARS_Main.VERSION_ID + " CARS_InfoApp";
  }

  /** getNodes
   *
   * @param pMain
   * @param pInterfaceNode
   * @param pParentNode
   * @param pLeaf
   * @throws java.lang.Exception
   */
  @Override
  public void getNodes( final CARS_Main pMain, final Node pInterfaceNode, final Node pParentNode, final String pLeaf ) throws RepositoryException {

    if (pParentNode.isNodeType( "jecars:CARS_Interface" )) {
      final Session appSession = CARS_Factory.getSystemApplicationSession();
      synchronized( appSession ) {
        try {
          final Node pn = appSession.getRootNode().getNode( pParentNode.getPath().substring(1) );
          if (!pn.hasNode( "WhoAmI")) {
            pn.addNode( "WhoAmI", "jecars:unstructured" );
          }
        } finally {
          appSession.save();
        }
      }
    } else {
      if ("/InfoApp/WhoAmI".equals( pLeaf )) {
        final Node user = pMain.getLoginUser();
        pParentNode.setProperty( "jecars:Username", user.getName() );
      }

    }

    return;
  }  
    
}

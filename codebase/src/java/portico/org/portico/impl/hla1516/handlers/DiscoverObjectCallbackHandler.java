/*
 *   Copyright 2008 The Portico Project
 *
 *   This file is part of portico.
 *
 *   portico is free software; you can redistribute it and/or modify
 *   it under the terms of the Common Developer and Distribution License (CDDL) 
 *   as published by Sun Microsystems. For more information see the LICENSE file.
 *   
 *   Use of this software is strictly AT YOUR OWN RISK!!!
 *   If something bad happens you do not have permission to come crying to me.
 *   (that goes for your lawyer as well)
 *
 */
package org.portico.impl.hla1516.handlers;

import hla.rti1516.ObjectClassHandle;
import hla.rti1516.ObjectInstanceHandle;

import java.util.Map;

import org.portico.impl.hla1516.Impl1516Helper;
import org.portico.impl.hla1516.types.HLA1516ObjectClassHandle;
import org.portico.impl.hla1516.types.HLA1516ObjectInstanceHandle;
import org.portico.lrc.LRCMessageHandler;
import org.portico.lrc.services.object.msg.DiscoverObject;
import org.portico.utils.messaging.MessageContext;
import org.portico.utils.messaging.MessageHandler;

/**
 * Generate discoverObjectInstance() callbacks to a IEEE1516 compliant federate ambassador
 */
@MessageHandler(modules="lrc1516-callback",
                keywords="lrc1516",
                sinks="incoming",
                priority=3,
                messages=DiscoverObject.class)
public class DiscoverObjectCallbackHandler extends LRCMessageHandler
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private Impl1516Helper helper;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	public void initialize( Map<String,Object> properties )
	{
		super.initialize( properties );
		this.helper = (Impl1516Helper)lrc.getSpecHelper();
	}
	
	public void process( MessageContext context ) throws Exception
	{
		DiscoverObject request = context.getRequest( DiscoverObject.class, this );
		int objectHandle = request.getObjectHandle();
		int classHandle = request.getClassHandle();
		String objectName = request.getObjectName();

		if( logger.isTraceEnabled() )
		{
			logger.trace( "CALLBACK discoverObjectInstance(object="+objectHandle+
			              ",class="+ classHandle+",name="+objectName+")" );
		}
		
		// do the callback
		ObjectInstanceHandle oHandle = new HLA1516ObjectInstanceHandle( objectHandle );
		ObjectClassHandle cHandle = new HLA1516ObjectClassHandle( classHandle );
		helper.getFederateAmbassador().discoverObjectInstance( oHandle, cHandle, objectName );
		context.success();
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}

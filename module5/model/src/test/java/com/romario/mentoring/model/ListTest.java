package com.romario.mentoring.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;


public class ListTest
{
  @Test
  public void addToListTest()
  {
    List<String> list = new ArrayList<String>();
    list.add( "one" );
    list.add( "two" );

    assertThat( list, hasItem( "one" ) );
    assertThat( list, contains( "one", "two" ) );
    assertThat( list, containsInAnyOrder( "two", "one" ) );
    assertThat( list, iterableWithSize( 2 ) );

    assertThat( list, hasSize( 2 ) );
    assertThat( list.toArray(), not( emptyArray() ) );
    assertThat( list.toArray(), hasItemInArray( equalTo( "two" ) ) );
    assertThat( list, notNullValue() );

    assertThat( list.get( 0 ), equalTo( "one" ) );
    assertThat( list.get( 0 ), equalToIgnoringCase( "ONe" ) );
    assertThat( list.get( 0 ), equalToIgnoringWhiteSpace( "   one    " ) );
    assertThat( list.get( 0 ), endsWith( "ne" ) );
    assertThat( list.get( 0 ), not( isEmptyString() ) );
  }

}

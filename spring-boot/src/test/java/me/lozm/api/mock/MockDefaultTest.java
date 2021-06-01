package me.lozm.api.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MockDefaultTest {

    private final String APPLE = "apple";
    private final String KIWI = "kiwi";
    private final String MELON = "melon";

    @Test
    void stubbingTestUsingWhen1() {
        // Given
        final ArrayList mockList = Mockito.mock(ArrayList.class);

        // When
//        mockList.add(APPLE);
        Mockito.when(mockList.get(0)).thenReturn(APPLE);
        Mockito.when(mockList.get(1)).thenReturn(KIWI);
        Mockito.when(mockList.get(2)).thenReturn(MELON);
        Mockito.when(mockList.size()).thenReturn(3);

        // Then
        Assertions.assertEquals(APPLE, mockList.get(0));
        Assertions.assertEquals(KIWI, mockList.get(1));
        Assertions.assertEquals(MELON, mockList.get(2));
        Assertions.assertEquals(3, mockList.size());
    }

    @Test
    void stubbingTestUsingWhen2() {
        // Given
        final ArrayList mockList = Mockito.mock(ArrayList.class);

        // When
        Mockito.when(mockList.get(Mockito.anyInt())).thenReturn(APPLE);

        // Then
        Assertions.assertEquals(APPLE, mockList.get(0));
        Assertions.assertEquals(APPLE, mockList.get(1));
        Assertions.assertEquals(APPLE, mockList.get(2));
    }

    @Test
    void verifyTest1() {
        // Given
        final ArrayList mockList = Mockito.mock(ArrayList.class);
        mockList.add(APPLE);
        Mockito.verify(mockList).add(APPLE);

        // When
        mockList.get(0);
        mockList.get(0);
        mockList.get(0);

        // Then
        Mockito.verify(mockList, Mockito.times(3)).get(0);
        Mockito.verify(mockList, Mockito.atLeast(2)).get(0);
    }

    @Test
    void argumentCaptorTest1() {
        // Given
        final ArrayList mockList = Mockito.mock(ArrayList.class);
        final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // When
        mockList.add(APPLE);
        Mockito.verify(mockList).add(stringArgumentCaptor.capture());

        // Then
        Assertions.assertEquals(APPLE, stringArgumentCaptor.getValue());
    }

    @Test
    void spyTest1() {
        // Given
        final ArrayList spyList = Mockito.spy(ArrayList.class);

        // When
        spyList.add(APPLE);

        // Then
        Assertions.assertEquals(APPLE, spyList.get(0));
    }

    @Test
    void spyTest2() {
        // Given
        final ArrayList spyList = Mockito.spy(ArrayList.class);

        // When
        spyList.add(APPLE);
        Mockito.verify(spyList).add(APPLE);
        spyList.get(0);

        // Then
        Assertions.assertEquals(APPLE, spyList.get(0));
        Mockito.verify(spyList, Mockito.atLeast(1)).get(0);
    }

    @Test
    void spyTest3() {
        // Given
        final ArrayList spyList = Mockito.spy(ArrayList.class);

        // When
        Mockito.doReturn(APPLE).when(spyList).get(0);
        Mockito.doReturn(KIWI).when(spyList).get(1);
        Mockito.doReturn(10).when(spyList).size();

        // Then
        Assertions.assertEquals(APPLE, spyList.get(0));
        Assertions.assertEquals(KIWI, spyList.get(1));
        Assertions.assertEquals(10, spyList.size());
    }

}

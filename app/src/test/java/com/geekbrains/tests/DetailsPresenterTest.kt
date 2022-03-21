package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {
    @Mock
    private lateinit var viewDetailsContract: ViewDetailsContract

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    //при вызове onIncrement должен запускаться setCount и устанавливать значение на 1 больше, чем заданное
    @Test
    fun detailsPresenter_Incrementing_ReturnsTrue() {
        val presenter = DetailsPresenter(EXPECTED_DEFAULT_VALUE)
        presenter.onAttach(viewDetailsContract)
        presenter.onIncrement()
        verify(viewDetailsContract).setCount(ArgumentMatchers.eq(ACTUAL_DEFAULT_INCREMENT_VALUE))
        presenter.onDetach()
    }

    //при вызове onDecrement должен запускаться setCount и устанавливать значение на 1 меньше, чем заданное
    @Test
    fun detailsPresenter_Decrementing_ReturnsTrue() {
        val presenter = DetailsPresenter(EXPECTED_DEFAULT_VALUE)
        presenter.onAttach(viewDetailsContract)
        presenter.onDecrement()
        verify(viewDetailsContract).setCount(ArgumentMatchers.eq(ACTUAL_DEFAULT_DECREMENT_VALUE))
        presenter.onDetach()
    }

    //onIncrement не должен срабатывать до вызова onAttach
    @Test
    fun detailsPresenter_IncrementingNotWorkingBeforeAttach_ReturnsTrue() {
        val presenter = DetailsPresenter(EXPECTED_DEFAULT_VALUE)
        presenter.onIncrement()
        presenter.onAttach(viewDetailsContract)
        Mockito.verify(viewDetailsContract, times(0))
            .setCount(ArgumentMatchers.eq(ACTUAL_DEFAULT_INCREMENT_VALUE))
        presenter.onDetach()
    }

    //onIncrement не должен срабатывать после вызова onDetach
    @Test
    fun detailsPresenter_IncrementingNotWorkingAfterDetach_ReturnsTrue() {
        val presenter = DetailsPresenter(EXPECTED_DEFAULT_VALUE)
        presenter.onAttach(viewDetailsContract)
        presenter.onDetach()
        presenter.onIncrement()
        Mockito.verify(viewDetailsContract, times(0))
            .setCount(ArgumentMatchers.eq(ACTUAL_DEFAULT_INCREMENT_VALUE))
    }

    //onDecrement не должен срабатывать до вызова onAttach
    @Test
    fun detailsPresenter_DecrementingNotWorkingBeforeAttach_ReturnsTrue() {
        val presenter = DetailsPresenter(EXPECTED_DEFAULT_VALUE)
        presenter.onDecrement()
        presenter.onAttach(viewDetailsContract)
        Mockito.verify(viewDetailsContract, times(0))
            .setCount(ArgumentMatchers.eq(ACTUAL_DEFAULT_INCREMENT_VALUE))
        presenter.onDetach()
    }

    //onDecrement не должен срабатывать после вызова onDetach
    @Test
    fun detailsPresenter_DecrementingNotWorkingAfterDetach_ReturnsTrue() {
        val presenter = DetailsPresenter(EXPECTED_DEFAULT_VALUE)
        presenter.onAttach(viewDetailsContract)
        presenter.onDetach()
        presenter.onDecrement()
        Mockito.verify(viewDetailsContract, times(0))
            .setCount(ArgumentMatchers.eq(ACTUAL_DEFAULT_INCREMENT_VALUE))
    }

    companion object {
        private const val EXPECTED_DEFAULT_VALUE = 3
        private const val ACTUAL_DEFAULT_INCREMENT_VALUE = 4
        private const val ACTUAL_DEFAULT_DECREMENT_VALUE = 2
    }
}
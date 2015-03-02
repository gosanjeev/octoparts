package com.m3.octoparts.repository

import com.m3.octoparts.model.config._
import com.twitter.zipkin.gen.Span

import scala.concurrent.Future

/**
 * A ConfigsRepository is an abstract DAO that makes no assumptions about
 * the persistence-layer that may implement it
 *
 * This is useful so that we can decorate all calls to the actual persistence
 * layers, for example with caching.
 */
trait ConfigsRepository {
  /**
   * Wrapped version of a standard get operation that works much like Map#get
   */
  def findConfigByPartId(partId: String)(implicit parentSpan: Span): Future[Option[HttpPartConfig]]

  /**
   * Returns all the configs from the repository
   */
  def findAllConfigs()(implicit parentSpan: Span): Future[Seq[HttpPartConfig]]

  def findParamById(id: Long)(implicit parentSpan: Span): Future[Option[PartParam]]

  /**
   * Find a single ThreadPoolConfig by id
   */
  def findThreadPoolConfigById(id: Long)(implicit parentSpan: Span): Future[Option[ThreadPoolConfig]]

  /**
   * Return all the thread pool configs
   */
  def findAllThreadPoolConfigs()(implicit parentSpan: Span): Future[Seq[ThreadPoolConfig]]

  /**
   * Returns a single cache group by name
   */
  def findCacheGroupByName(name: String)(implicit parentSpan: Span): Future[Option[CacheGroup]]

  /**
   * Return all cache groups
   */
  def findAllCacheGroups()(implicit parentSpan: Span): Future[Seq[CacheGroup]]

  /**
   * Return all cache groups with the given names
   */
  def findAllCacheGroupsByName(name: String*)(implicit parentSpan: Span): Future[Seq[CacheGroup]]

}

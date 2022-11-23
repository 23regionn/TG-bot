import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IChanell } from 'app/shared/model/chanell.model';
import { getEntities as getChanells } from 'app/entities/chanell/chanell.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { getEntity, updateEntity, createEntity, reset } from './links-by-category-in-top.reducer';
import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILinksByCategoryInTopUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LinksByCategoryInTopUpdate = (props: ILinksByCategoryInTopUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { linksByCategoryInTopEntity, chanells, categories, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/links-by-category-in-top');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getChanells();
    props.getCategories();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.datePostLinkStart = convertDateTimeToServer(values.datePostLinkStart);
    values.datePostLinkEnd = convertDateTimeToServer(values.datePostLinkEnd);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...linksByCategoryInTopEntity,
        ...values,
        chanell: chanells.find(it => it.id.toString() === values.chanellId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gidApp.linksByCategoryInTop.home.createOrEditLabel" data-cy="LinksByCategoryInTopCreateUpdateHeading">
            Create or edit a LinksByCategoryInTop
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : linksByCategoryInTopEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="links-by-category-in-top-id">ID</Label>
                  <AvInput id="links-by-category-in-top-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="categoryLabel" for="links-by-category-in-top-category">
                  Category
                </Label>
                <AvField id="links-by-category-in-top-category" data-cy="category" type="text" name="category" />
                <UncontrolledTooltip target="categoryLabel">категория</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="priceDiapozonLabel" for="links-by-category-in-top-priceDiapozon">
                  Price Diapozon
                </Label>
                <AvField
                  id="links-by-category-in-top-priceDiapozon"
                  data-cy="priceDiapozon"
                  type="string"
                  className="form-control"
                  name="priceDiapozon"
                />
                <UncontrolledTooltip target="priceDiapozonLabel">ценовой диапозон</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="links-by-category-in-top-link">
                  Link
                </Label>
                <AvField id="links-by-category-in-top-link" data-cy="link" type="text" name="link" />
                <UncontrolledTooltip target="linkLabel">ссылку на канал</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="chanellAdminIdLabel" for="links-by-category-in-top-chanellAdminId">
                  Chanell Admin Id
                </Label>
                <AvField
                  id="links-by-category-in-top-chanellAdminId"
                  data-cy="chanellAdminId"
                  type="string"
                  className="form-control"
                  name="chanellAdminId"
                />
                <UncontrolledTooltip target="chanellAdminIdLabel">ссылка на админа</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="datePostLinkStartLabel" for="links-by-category-in-top-datePostLinkStart">
                  Date Post Link Start
                </Label>
                <AvInput
                  id="links-by-category-in-top-datePostLinkStart"
                  data-cy="datePostLinkStart"
                  type="datetime-local"
                  className="form-control"
                  name="datePostLinkStart"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopEntity.datePostLinkStart)}
                />
                <UncontrolledTooltip target="datePostLinkStartLabel">дата размещения ссылки</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="datePostLinkEndLabel" for="links-by-category-in-top-datePostLinkEnd">
                  Date Post Link End
                </Label>
                <AvInput
                  id="links-by-category-in-top-datePostLinkEnd"
                  data-cy="datePostLinkEnd"
                  type="datetime-local"
                  className="form-control"
                  name="datePostLinkEnd"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopEntity.datePostLinkEnd)}
                />
                <UncontrolledTooltip target="datePostLinkEndLabel">дата окончания размещения</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="positionBetweenLinksLabel" for="links-by-category-in-top-positionBetweenLinks">
                  Position Between Links
                </Label>
                <AvField
                  id="links-by-category-in-top-positionBetweenLinks"
                  data-cy="positionBetweenLinks"
                  type="string"
                  className="form-control"
                  name="positionBetweenLinks"
                />
                <UncontrolledTooltip target="positionBetweenLinksLabel">место среди ссылок</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="showLinkLabel">
                  <AvInput
                    id="links-by-category-in-top-showLink"
                    data-cy="showLink"
                    type="checkbox"
                    className="form-check-input"
                    name="showLink"
                  />
                  Show Link
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput
                    id="links-by-category-in-top-isDelete"
                    data-cy="isDelete"
                    type="checkbox"
                    className="form-check-input"
                    name="isDelete"
                  />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="links-by-category-in-top-date1">
                  Date 1
                </Label>
                <AvInput
                  id="links-by-category-in-top-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="links-by-category-in-top-date2">
                  Date 2
                </Label>
                <AvInput
                  id="links-by-category-in-top-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="links-by-category-in-top-long1">
                  Long 1
                </Label>
                <AvField id="links-by-category-in-top-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="links-by-category-in-top-string1">
                  String 1
                </Label>
                <AvField id="links-by-category-in-top-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput
                    id="links-by-category-in-top-boolean1"
                    data-cy="boolean1"
                    type="checkbox"
                    className="form-check-input"
                    name="boolean1"
                  />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="links-by-category-in-top-chanell">Chanell</Label>
                <AvInput id="links-by-category-in-top-chanell" data-cy="chanell" type="select" className="form-control" name="chanellId">
                  <option value="" key="0" />
                  {chanells
                    ? chanells.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/links-by-category-in-top" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  chanells: storeState.chanell.entities,
  categories: storeState.category.entities,
  linksByCategoryInTopEntity: storeState.linksByCategoryInTop.entity,
  loading: storeState.linksByCategoryInTop.loading,
  updating: storeState.linksByCategoryInTop.updating,
  updateSuccess: storeState.linksByCategoryInTop.updateSuccess,
});

const mapDispatchToProps = {
  getChanells,
  getCategories,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LinksByCategoryInTopUpdate);
